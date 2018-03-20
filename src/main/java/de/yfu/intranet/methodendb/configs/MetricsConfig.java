package de.yfu.intranet.methodendb.configs;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.writer.GaugeWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Configuration
@Component
@Profile(value = {"docker"})
public class MetricsConfig {

    @Value("${influx.host}")
    public String INFLUX_HOST;
    @Value("${influx.port}")
    public int INFLUX_PORT;

    public static final String DB_NAME = "grafana";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static InfluxDB influxDB;

    @Bean
    @ExportMetricWriter
    GaugeWriter influxMetricsWriter() {
        try {
            influxDB = InfluxDBFactory.connect(INFLUX_HOST + ":" + INFLUX_PORT, "root", "root");
            influxDB.setDatabase(DB_NAME);
            influxDB.setRetentionPolicy("one_day");
            influxDB.enableBatch(10, 1000, TimeUnit.MILLISECONDS);
        }
        catch (Exception e) {
            logger.error("Connection to Influx failed. No data will be logged. Error: {}", e.getMessage());
        }

        return new GaugeWriter() {

            @Override
            public void set(Metric<?> value) {
                Point point = Point.measurement(value.getName()).time(value.getTimestamp().getTime(), TimeUnit.MILLISECONDS)
                        .addField("value", value.getValue()).build();
                try {
                    influxDB.write(point);
                }
                catch (Exception e) {
                    logger.error("Writing Metrics point to Influx failed. Error: {}", e.toString());
                }
                //logger.info("write(" + value.getName() + "): " + value.getValue());
            }
        };
    }

    @Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(final MetricsEndpoint metricsEndpoint) {
        return new MetricsEndpointMetricReader(metricsEndpoint);
    }

}
