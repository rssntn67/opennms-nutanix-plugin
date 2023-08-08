
package org.opennms.nutanix.client.v1.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hypervisor_avg_io_latency_usecs",
    "num_read_iops",
    "hypervisor_write_io_bandwidth_kBps",
    "timespan_usecs",
    "controller_num_read_iops",
    "controller.storage_tier.ssd.usage_bytes",
    "read_io_ppm",
    "controller_num_iops",
    "hypervisor_memory_assigned_bytes",
    "total_read_io_time_usecs",
    "controller_total_read_io_time_usecs",
    "controller.storage_tier.ssd.configured_pinned_bytes",
    "hypervisor_num_io",
    "controller_total_transformed_usage_bytes",
    "hypervisor_cpu_usage_ppm",
    "controller_num_write_io",
    "avg_read_io_latency_usecs",
    "guest.memory_swapped_in_bytes",
    "controller_total_io_time_usecs",
    "memory_usage_ppm",
    "controller_total_read_io_size_kbytes",
    "controller_num_seq_io",
    "controller_read_io_ppm",
    "controller_total_io_size_kbytes",
    "hypervisor.cpu_ready_time_ppm",
    "controller_num_io",
    "hypervisor_avg_read_io_latency_usecs",
    "num_write_iops",
    "controller_num_random_io",
    "num_iops",
    "guest.memory_usage_ppm",
    "hypervisor_num_read_io",
    "hypervisor_total_read_io_time_usecs",
    "controller_avg_io_latency_usecs",
    "num_io",
    "controller_num_read_io",
    "hypervisor_num_write_io",
    "controller_seq_io_ppm",
    "guest.memory_usage_bytes",
    "controller_read_io_bandwidth_kBps",
    "controller_io_bandwidth_kBps",
    "hypervisor_num_received_bytes",
    "hypervisor_timespan_usecs",
    "hypervisor_num_write_iops",
    "total_read_io_size_kbytes",
    "hypervisor_total_io_size_kbytes",
    "avg_io_latency_usecs",
    "hypervisor_num_read_iops",
    "hypervisor_swap_in_rate_kBps",
    "controller_write_io_bandwidth_kBps",
    "controller_write_io_ppm",
    "controller_user_bytes",
    "hypervisor_avg_write_io_latency_usecs",
    "hypervisor_num_transmitted_bytes",
    "hypervisor_total_read_io_size_kbytes",
    "read_io_bandwidth_kBps",
    "guest.memory_swapped_out_bytes",
    "hypervisor_memory_usage_ppm",
    "hypervisor_num_iops",
    "hypervisor_io_bandwidth_kBps",
    "controller_num_write_iops",
    "total_io_time_usecs",
    "controller_random_io_ppm",
    "controller.storage_tier.das-sata.usage_bytes",
    "controller_avg_read_io_size_kbytes",
    "hypervisor_swap_out_rate_kBps",
    "total_transformed_usage_bytes",
    "avg_write_io_latency_usecs",
    "num_read_io",
    "write_io_bandwidth_kBps",
    "hypervisor_read_io_bandwidth_kBps",
    "hypervisor_consumed_memory_bytes",
    "random_io_ppm",
    "total_untransformed_usage_bytes",
    "hypervisor_total_io_time_usecs",
    "num_random_io",
    "controller_avg_write_io_size_kbytes",
    "controller_avg_read_io_latency_usecs",
    "controller.storage_tier.das-sata.configured_pinned_bytes",
    "num_write_io",
    "total_io_size_kbytes",
    "controller.storage_tier.cloud.usage_bytes",
    "io_bandwidth_kBps",
    "controller_timespan_usecs",
    "num_seq_io",
    "seq_io_ppm",
    "write_io_ppm",
    "controller_avg_write_io_latency_usecs"
})
@Generated("jsonschema2pojo")
public class Stats {

    @JsonProperty("hypervisor_avg_io_latency_usecs")
    private String hypervisorAvgIoLatencyUsecs;
    @JsonProperty("num_read_iops")
    private String numReadIops;
    @JsonProperty("hypervisor_write_io_bandwidth_kBps")
    private String hypervisorWriteIoBandwidthKBps;
    @JsonProperty("timespan_usecs")
    private String timespanUsecs;
    @JsonProperty("controller_num_read_iops")
    private String controllerNumReadIops;
    @JsonProperty("controller.storage_tier.ssd.usage_bytes")
    private String controllerStorageTierSsdUsageBytes;
    @JsonProperty("read_io_ppm")
    private String readIoPpm;
    @JsonProperty("controller_num_iops")
    private String controllerNumIops;
    @JsonProperty("hypervisor_memory_assigned_bytes")
    private String hypervisorMemoryAssignedBytes;
    @JsonProperty("total_read_io_time_usecs")
    private String totalReadIoTimeUsecs;
    @JsonProperty("controller_total_read_io_time_usecs")
    private String controllerTotalReadIoTimeUsecs;
    @JsonProperty("controller.storage_tier.ssd.configured_pinned_bytes")
    private String controllerStorageTierSsdConfiguredPinnedBytes;
    @JsonProperty("hypervisor_num_io")
    private String hypervisorNumIo;
    @JsonProperty("controller_total_transformed_usage_bytes")
    private String controllerTotalTransformedUsageBytes;
    @JsonProperty("hypervisor_cpu_usage_ppm")
    private String hypervisorCpuUsagePpm;
    @JsonProperty("controller_num_write_io")
    private String controllerNumWriteIo;
    @JsonProperty("avg_read_io_latency_usecs")
    private String avgReadIoLatencyUsecs;
    @JsonProperty("guest.memory_swapped_in_bytes")
    private String guestMemorySwappedInBytes;
    @JsonProperty("controller_total_io_time_usecs")
    private String controllerTotalIoTimeUsecs;
    @JsonProperty("memory_usage_ppm")
    private String memoryUsagePpm;
    @JsonProperty("controller_total_read_io_size_kbytes")
    private String controllerTotalReadIoSizeKbytes;
    @JsonProperty("controller_num_seq_io")
    private String controllerNumSeqIo;
    @JsonProperty("controller_read_io_ppm")
    private String controllerReadIoPpm;
    @JsonProperty("controller_total_io_size_kbytes")
    private String controllerTotalIoSizeKbytes;
    @JsonProperty("hypervisor.cpu_ready_time_ppm")
    private String hypervisorCpuReadyTimePpm;
    @JsonProperty("controller_num_io")
    private String controllerNumIo;
    @JsonProperty("hypervisor_avg_read_io_latency_usecs")
    private String hypervisorAvgReadIoLatencyUsecs;
    @JsonProperty("num_write_iops")
    private String numWriteIops;
    @JsonProperty("controller_num_random_io")
    private String controllerNumRandomIo;
    @JsonProperty("num_iops")
    private String numIops;
    @JsonProperty("guest.memory_usage_ppm")
    private String guestMemoryUsagePpm;
    @JsonProperty("hypervisor_num_read_io")
    private String hypervisorNumReadIo;
    @JsonProperty("hypervisor_total_read_io_time_usecs")
    private String hypervisorTotalReadIoTimeUsecs;
    @JsonProperty("controller_avg_io_latency_usecs")
    private String controllerAvgIoLatencyUsecs;
    @JsonProperty("num_io")
    private String numIo;
    @JsonProperty("controller_num_read_io")
    private String controllerNumReadIo;
    @JsonProperty("hypervisor_num_write_io")
    private String hypervisorNumWriteIo;
    @JsonProperty("controller_seq_io_ppm")
    private String controllerSeqIoPpm;
    @JsonProperty("guest.memory_usage_bytes")
    private String guestMemoryUsageBytes;
    @JsonProperty("controller_read_io_bandwidth_kBps")
    private String controllerReadIoBandwidthKBps;
    @JsonProperty("controller_io_bandwidth_kBps")
    private String controllerIoBandwidthKBps;
    @JsonProperty("hypervisor_num_received_bytes")
    private String hypervisorNumReceivedBytes;
    @JsonProperty("hypervisor_timespan_usecs")
    private String hypervisorTimespanUsecs;
    @JsonProperty("hypervisor_num_write_iops")
    private String hypervisorNumWriteIops;
    @JsonProperty("total_read_io_size_kbytes")
    private String totalReadIoSizeKbytes;
    @JsonProperty("hypervisor_total_io_size_kbytes")
    private String hypervisorTotalIoSizeKbytes;
    @JsonProperty("avg_io_latency_usecs")
    private String avgIoLatencyUsecs;
    @JsonProperty("hypervisor_num_read_iops")
    private String hypervisorNumReadIops;
    @JsonProperty("hypervisor_swap_in_rate_kBps")
    private String hypervisorSwapInRateKBps;
    @JsonProperty("controller_write_io_bandwidth_kBps")
    private String controllerWriteIoBandwidthKBps;
    @JsonProperty("controller_write_io_ppm")
    private String controllerWriteIoPpm;
    @JsonProperty("controller_user_bytes")
    private String controllerUserBytes;
    @JsonProperty("hypervisor_avg_write_io_latency_usecs")
    private String hypervisorAvgWriteIoLatencyUsecs;
    @JsonProperty("hypervisor_num_transmitted_bytes")
    private String hypervisorNumTransmittedBytes;
    @JsonProperty("hypervisor_total_read_io_size_kbytes")
    private String hypervisorTotalReadIoSizeKbytes;
    @JsonProperty("read_io_bandwidth_kBps")
    private String readIoBandwidthKBps;
    @JsonProperty("guest.memory_swapped_out_bytes")
    private String guestMemorySwappedOutBytes;
    @JsonProperty("hypervisor_memory_usage_ppm")
    private String hypervisorMemoryUsagePpm;
    @JsonProperty("hypervisor_num_iops")
    private String hypervisorNumIops;
    @JsonProperty("hypervisor_io_bandwidth_kBps")
    private String hypervisorIoBandwidthKBps;
    @JsonProperty("controller_num_write_iops")
    private String controllerNumWriteIops;
    @JsonProperty("total_io_time_usecs")
    private String totalIoTimeUsecs;
    @JsonProperty("controller_random_io_ppm")
    private String controllerRandomIoPpm;
    @JsonProperty("controller.storage_tier.das-sata.usage_bytes")
    private String controllerStorageTierDasSataUsageBytes;
    @JsonProperty("controller_avg_read_io_size_kbytes")
    private String controllerAvgReadIoSizeKbytes;
    @JsonProperty("hypervisor_swap_out_rate_kBps")
    private String hypervisorSwapOutRateKBps;
    @JsonProperty("total_transformed_usage_bytes")
    private String totalTransformedUsageBytes;
    @JsonProperty("avg_write_io_latency_usecs")
    private String avgWriteIoLatencyUsecs;
    @JsonProperty("num_read_io")
    private String numReadIo;
    @JsonProperty("write_io_bandwidth_kBps")
    private String writeIoBandwidthKBps;
    @JsonProperty("hypervisor_read_io_bandwidth_kBps")
    private String hypervisorReadIoBandwidthKBps;
    @JsonProperty("hypervisor_consumed_memory_bytes")
    private String hypervisorConsumedMemoryBytes;
    @JsonProperty("random_io_ppm")
    private String randomIoPpm;
    @JsonProperty("total_untransformed_usage_bytes")
    private String totalUntransformedUsageBytes;
    @JsonProperty("hypervisor_total_io_time_usecs")
    private String hypervisorTotalIoTimeUsecs;
    @JsonProperty("num_random_io")
    private String numRandomIo;
    @JsonProperty("controller_avg_write_io_size_kbytes")
    private String controllerAvgWriteIoSizeKbytes;
    @JsonProperty("controller_avg_read_io_latency_usecs")
    private String controllerAvgReadIoLatencyUsecs;
    @JsonProperty("controller.storage_tier.das-sata.configured_pinned_bytes")
    private String controllerStorageTierDasSataConfiguredPinnedBytes;
    @JsonProperty("num_write_io")
    private String numWriteIo;
    @JsonProperty("total_io_size_kbytes")
    private String totalIoSizeKbytes;
    @JsonProperty("controller.storage_tier.cloud.usage_bytes")
    private String controllerStorageTierCloudUsageBytes;
    @JsonProperty("io_bandwidth_kBps")
    private String ioBandwidthKBps;
    @JsonProperty("controller_timespan_usecs")
    private String controllerTimespanUsecs;
    @JsonProperty("num_seq_io")
    private String numSeqIo;
    @JsonProperty("seq_io_ppm")
    private String seqIoPpm;
    @JsonProperty("write_io_ppm")
    private String writeIoPpm;
    @JsonProperty("controller_avg_write_io_latency_usecs")
    private String controllerAvgWriteIoLatencyUsecs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("hypervisor_avg_io_latency_usecs")
    public String getHypervisorAvgIoLatencyUsecs() {
        return hypervisorAvgIoLatencyUsecs;
    }

    @JsonProperty("hypervisor_avg_io_latency_usecs")
    public void setHypervisorAvgIoLatencyUsecs(String hypervisorAvgIoLatencyUsecs) {
        this.hypervisorAvgIoLatencyUsecs = hypervisorAvgIoLatencyUsecs;
    }

    @JsonProperty("num_read_iops")
    public String getNumReadIops() {
        return numReadIops;
    }

    @JsonProperty("num_read_iops")
    public void setNumReadIops(String numReadIops) {
        this.numReadIops = numReadIops;
    }

    @JsonProperty("hypervisor_write_io_bandwidth_kBps")
    public String getHypervisorWriteIoBandwidthKBps() {
        return hypervisorWriteIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_write_io_bandwidth_kBps")
    public void setHypervisorWriteIoBandwidthKBps(String hypervisorWriteIoBandwidthKBps) {
        this.hypervisorWriteIoBandwidthKBps = hypervisorWriteIoBandwidthKBps;
    }

    @JsonProperty("timespan_usecs")
    public String getTimespanUsecs() {
        return timespanUsecs;
    }

    @JsonProperty("timespan_usecs")
    public void setTimespanUsecs(String timespanUsecs) {
        this.timespanUsecs = timespanUsecs;
    }

    @JsonProperty("controller_num_read_iops")
    public String getControllerNumReadIops() {
        return controllerNumReadIops;
    }

    @JsonProperty("controller_num_read_iops")
    public void setControllerNumReadIops(String controllerNumReadIops) {
        this.controllerNumReadIops = controllerNumReadIops;
    }

    @JsonProperty("controller.storage_tier.ssd.usage_bytes")
    public String getControllerStorageTierSsdUsageBytes() {
        return controllerStorageTierSsdUsageBytes;
    }

    @JsonProperty("controller.storage_tier.ssd.usage_bytes")
    public void setControllerStorageTierSsdUsageBytes(String controllerStorageTierSsdUsageBytes) {
        this.controllerStorageTierSsdUsageBytes = controllerStorageTierSsdUsageBytes;
    }

    @JsonProperty("read_io_ppm")
    public String getReadIoPpm() {
        return readIoPpm;
    }

    @JsonProperty("read_io_ppm")
    public void setReadIoPpm(String readIoPpm) {
        this.readIoPpm = readIoPpm;
    }

    @JsonProperty("controller_num_iops")
    public String getControllerNumIops() {
        return controllerNumIops;
    }

    @JsonProperty("controller_num_iops")
    public void setControllerNumIops(String controllerNumIops) {
        this.controllerNumIops = controllerNumIops;
    }

    @JsonProperty("hypervisor_memory_assigned_bytes")
    public String getHypervisorMemoryAssignedBytes() {
        return hypervisorMemoryAssignedBytes;
    }

    @JsonProperty("hypervisor_memory_assigned_bytes")
    public void setHypervisorMemoryAssignedBytes(String hypervisorMemoryAssignedBytes) {
        this.hypervisorMemoryAssignedBytes = hypervisorMemoryAssignedBytes;
    }

    @JsonProperty("total_read_io_time_usecs")
    public String getTotalReadIoTimeUsecs() {
        return totalReadIoTimeUsecs;
    }

    @JsonProperty("total_read_io_time_usecs")
    public void setTotalReadIoTimeUsecs(String totalReadIoTimeUsecs) {
        this.totalReadIoTimeUsecs = totalReadIoTimeUsecs;
    }

    @JsonProperty("controller_total_read_io_time_usecs")
    public String getControllerTotalReadIoTimeUsecs() {
        return controllerTotalReadIoTimeUsecs;
    }

    @JsonProperty("controller_total_read_io_time_usecs")
    public void setControllerTotalReadIoTimeUsecs(String controllerTotalReadIoTimeUsecs) {
        this.controllerTotalReadIoTimeUsecs = controllerTotalReadIoTimeUsecs;
    }

    @JsonProperty("controller.storage_tier.ssd.configured_pinned_bytes")
    public String getControllerStorageTierSsdConfiguredPinnedBytes() {
        return controllerStorageTierSsdConfiguredPinnedBytes;
    }

    @JsonProperty("controller.storage_tier.ssd.configured_pinned_bytes")
    public void setControllerStorageTierSsdConfiguredPinnedBytes(String controllerStorageTierSsdConfiguredPinnedBytes) {
        this.controllerStorageTierSsdConfiguredPinnedBytes = controllerStorageTierSsdConfiguredPinnedBytes;
    }

    @JsonProperty("hypervisor_num_io")
    public String getHypervisorNumIo() {
        return hypervisorNumIo;
    }

    @JsonProperty("hypervisor_num_io")
    public void setHypervisorNumIo(String hypervisorNumIo) {
        this.hypervisorNumIo = hypervisorNumIo;
    }

    @JsonProperty("controller_total_transformed_usage_bytes")
    public String getControllerTotalTransformedUsageBytes() {
        return controllerTotalTransformedUsageBytes;
    }

    @JsonProperty("controller_total_transformed_usage_bytes")
    public void setControllerTotalTransformedUsageBytes(String controllerTotalTransformedUsageBytes) {
        this.controllerTotalTransformedUsageBytes = controllerTotalTransformedUsageBytes;
    }

    @JsonProperty("hypervisor_cpu_usage_ppm")
    public String getHypervisorCpuUsagePpm() {
        return hypervisorCpuUsagePpm;
    }

    @JsonProperty("hypervisor_cpu_usage_ppm")
    public void setHypervisorCpuUsagePpm(String hypervisorCpuUsagePpm) {
        this.hypervisorCpuUsagePpm = hypervisorCpuUsagePpm;
    }

    @JsonProperty("controller_num_write_io")
    public String getControllerNumWriteIo() {
        return controllerNumWriteIo;
    }

    @JsonProperty("controller_num_write_io")
    public void setControllerNumWriteIo(String controllerNumWriteIo) {
        this.controllerNumWriteIo = controllerNumWriteIo;
    }

    @JsonProperty("avg_read_io_latency_usecs")
    public String getAvgReadIoLatencyUsecs() {
        return avgReadIoLatencyUsecs;
    }

    @JsonProperty("avg_read_io_latency_usecs")
    public void setAvgReadIoLatencyUsecs(String avgReadIoLatencyUsecs) {
        this.avgReadIoLatencyUsecs = avgReadIoLatencyUsecs;
    }

    @JsonProperty("guest.memory_swapped_in_bytes")
    public String getGuestMemorySwappedInBytes() {
        return guestMemorySwappedInBytes;
    }

    @JsonProperty("guest.memory_swapped_in_bytes")
    public void setGuestMemorySwappedInBytes(String guestMemorySwappedInBytes) {
        this.guestMemorySwappedInBytes = guestMemorySwappedInBytes;
    }

    @JsonProperty("controller_total_io_time_usecs")
    public String getControllerTotalIoTimeUsecs() {
        return controllerTotalIoTimeUsecs;
    }

    @JsonProperty("controller_total_io_time_usecs")
    public void setControllerTotalIoTimeUsecs(String controllerTotalIoTimeUsecs) {
        this.controllerTotalIoTimeUsecs = controllerTotalIoTimeUsecs;
    }

    @JsonProperty("memory_usage_ppm")
    public String getMemoryUsagePpm() {
        return memoryUsagePpm;
    }

    @JsonProperty("memory_usage_ppm")
    public void setMemoryUsagePpm(String memoryUsagePpm) {
        this.memoryUsagePpm = memoryUsagePpm;
    }

    @JsonProperty("controller_total_read_io_size_kbytes")
    public String getControllerTotalReadIoSizeKbytes() {
        return controllerTotalReadIoSizeKbytes;
    }

    @JsonProperty("controller_total_read_io_size_kbytes")
    public void setControllerTotalReadIoSizeKbytes(String controllerTotalReadIoSizeKbytes) {
        this.controllerTotalReadIoSizeKbytes = controllerTotalReadIoSizeKbytes;
    }

    @JsonProperty("controller_num_seq_io")
    public String getControllerNumSeqIo() {
        return controllerNumSeqIo;
    }

    @JsonProperty("controller_num_seq_io")
    public void setControllerNumSeqIo(String controllerNumSeqIo) {
        this.controllerNumSeqIo = controllerNumSeqIo;
    }

    @JsonProperty("controller_read_io_ppm")
    public String getControllerReadIoPpm() {
        return controllerReadIoPpm;
    }

    @JsonProperty("controller_read_io_ppm")
    public void setControllerReadIoPpm(String controllerReadIoPpm) {
        this.controllerReadIoPpm = controllerReadIoPpm;
    }

    @JsonProperty("controller_total_io_size_kbytes")
    public String getControllerTotalIoSizeKbytes() {
        return controllerTotalIoSizeKbytes;
    }

    @JsonProperty("controller_total_io_size_kbytes")
    public void setControllerTotalIoSizeKbytes(String controllerTotalIoSizeKbytes) {
        this.controllerTotalIoSizeKbytes = controllerTotalIoSizeKbytes;
    }

    @JsonProperty("hypervisor.cpu_ready_time_ppm")
    public String getHypervisorCpuReadyTimePpm() {
        return hypervisorCpuReadyTimePpm;
    }

    @JsonProperty("hypervisor.cpu_ready_time_ppm")
    public void setHypervisorCpuReadyTimePpm(String hypervisorCpuReadyTimePpm) {
        this.hypervisorCpuReadyTimePpm = hypervisorCpuReadyTimePpm;
    }

    @JsonProperty("controller_num_io")
    public String getControllerNumIo() {
        return controllerNumIo;
    }

    @JsonProperty("controller_num_io")
    public void setControllerNumIo(String controllerNumIo) {
        this.controllerNumIo = controllerNumIo;
    }

    @JsonProperty("hypervisor_avg_read_io_latency_usecs")
    public String getHypervisorAvgReadIoLatencyUsecs() {
        return hypervisorAvgReadIoLatencyUsecs;
    }

    @JsonProperty("hypervisor_avg_read_io_latency_usecs")
    public void setHypervisorAvgReadIoLatencyUsecs(String hypervisorAvgReadIoLatencyUsecs) {
        this.hypervisorAvgReadIoLatencyUsecs = hypervisorAvgReadIoLatencyUsecs;
    }

    @JsonProperty("num_write_iops")
    public String getNumWriteIops() {
        return numWriteIops;
    }

    @JsonProperty("num_write_iops")
    public void setNumWriteIops(String numWriteIops) {
        this.numWriteIops = numWriteIops;
    }

    @JsonProperty("controller_num_random_io")
    public String getControllerNumRandomIo() {
        return controllerNumRandomIo;
    }

    @JsonProperty("controller_num_random_io")
    public void setControllerNumRandomIo(String controllerNumRandomIo) {
        this.controllerNumRandomIo = controllerNumRandomIo;
    }

    @JsonProperty("num_iops")
    public String getNumIops() {
        return numIops;
    }

    @JsonProperty("num_iops")
    public void setNumIops(String numIops) {
        this.numIops = numIops;
    }

    @JsonProperty("guest.memory_usage_ppm")
    public String getGuestMemoryUsagePpm() {
        return guestMemoryUsagePpm;
    }

    @JsonProperty("guest.memory_usage_ppm")
    public void setGuestMemoryUsagePpm(String guestMemoryUsagePpm) {
        this.guestMemoryUsagePpm = guestMemoryUsagePpm;
    }

    @JsonProperty("hypervisor_num_read_io")
    public String getHypervisorNumReadIo() {
        return hypervisorNumReadIo;
    }

    @JsonProperty("hypervisor_num_read_io")
    public void setHypervisorNumReadIo(String hypervisorNumReadIo) {
        this.hypervisorNumReadIo = hypervisorNumReadIo;
    }

    @JsonProperty("hypervisor_total_read_io_time_usecs")
    public String getHypervisorTotalReadIoTimeUsecs() {
        return hypervisorTotalReadIoTimeUsecs;
    }

    @JsonProperty("hypervisor_total_read_io_time_usecs")
    public void setHypervisorTotalReadIoTimeUsecs(String hypervisorTotalReadIoTimeUsecs) {
        this.hypervisorTotalReadIoTimeUsecs = hypervisorTotalReadIoTimeUsecs;
    }

    @JsonProperty("controller_avg_io_latency_usecs")
    public String getControllerAvgIoLatencyUsecs() {
        return controllerAvgIoLatencyUsecs;
    }

    @JsonProperty("controller_avg_io_latency_usecs")
    public void setControllerAvgIoLatencyUsecs(String controllerAvgIoLatencyUsecs) {
        this.controllerAvgIoLatencyUsecs = controllerAvgIoLatencyUsecs;
    }

    @JsonProperty("num_io")
    public String getNumIo() {
        return numIo;
    }

    @JsonProperty("num_io")
    public void setNumIo(String numIo) {
        this.numIo = numIo;
    }

    @JsonProperty("controller_num_read_io")
    public String getControllerNumReadIo() {
        return controllerNumReadIo;
    }

    @JsonProperty("controller_num_read_io")
    public void setControllerNumReadIo(String controllerNumReadIo) {
        this.controllerNumReadIo = controllerNumReadIo;
    }

    @JsonProperty("hypervisor_num_write_io")
    public String getHypervisorNumWriteIo() {
        return hypervisorNumWriteIo;
    }

    @JsonProperty("hypervisor_num_write_io")
    public void setHypervisorNumWriteIo(String hypervisorNumWriteIo) {
        this.hypervisorNumWriteIo = hypervisorNumWriteIo;
    }

    @JsonProperty("controller_seq_io_ppm")
    public String getControllerSeqIoPpm() {
        return controllerSeqIoPpm;
    }

    @JsonProperty("controller_seq_io_ppm")
    public void setControllerSeqIoPpm(String controllerSeqIoPpm) {
        this.controllerSeqIoPpm = controllerSeqIoPpm;
    }

    @JsonProperty("guest.memory_usage_bytes")
    public String getGuestMemoryUsageBytes() {
        return guestMemoryUsageBytes;
    }

    @JsonProperty("guest.memory_usage_bytes")
    public void setGuestMemoryUsageBytes(String guestMemoryUsageBytes) {
        this.guestMemoryUsageBytes = guestMemoryUsageBytes;
    }

    @JsonProperty("controller_read_io_bandwidth_kBps")
    public String getControllerReadIoBandwidthKBps() {
        return controllerReadIoBandwidthKBps;
    }

    @JsonProperty("controller_read_io_bandwidth_kBps")
    public void setControllerReadIoBandwidthKBps(String controllerReadIoBandwidthKBps) {
        this.controllerReadIoBandwidthKBps = controllerReadIoBandwidthKBps;
    }

    @JsonProperty("controller_io_bandwidth_kBps")
    public String getControllerIoBandwidthKBps() {
        return controllerIoBandwidthKBps;
    }

    @JsonProperty("controller_io_bandwidth_kBps")
    public void setControllerIoBandwidthKBps(String controllerIoBandwidthKBps) {
        this.controllerIoBandwidthKBps = controllerIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_num_received_bytes")
    public String getHypervisorNumReceivedBytes() {
        return hypervisorNumReceivedBytes;
    }

    @JsonProperty("hypervisor_num_received_bytes")
    public void setHypervisorNumReceivedBytes(String hypervisorNumReceivedBytes) {
        this.hypervisorNumReceivedBytes = hypervisorNumReceivedBytes;
    }

    @JsonProperty("hypervisor_timespan_usecs")
    public String getHypervisorTimespanUsecs() {
        return hypervisorTimespanUsecs;
    }

    @JsonProperty("hypervisor_timespan_usecs")
    public void setHypervisorTimespanUsecs(String hypervisorTimespanUsecs) {
        this.hypervisorTimespanUsecs = hypervisorTimespanUsecs;
    }

    @JsonProperty("hypervisor_num_write_iops")
    public String getHypervisorNumWriteIops() {
        return hypervisorNumWriteIops;
    }

    @JsonProperty("hypervisor_num_write_iops")
    public void setHypervisorNumWriteIops(String hypervisorNumWriteIops) {
        this.hypervisorNumWriteIops = hypervisorNumWriteIops;
    }

    @JsonProperty("total_read_io_size_kbytes")
    public String getTotalReadIoSizeKbytes() {
        return totalReadIoSizeKbytes;
    }

    @JsonProperty("total_read_io_size_kbytes")
    public void setTotalReadIoSizeKbytes(String totalReadIoSizeKbytes) {
        this.totalReadIoSizeKbytes = totalReadIoSizeKbytes;
    }

    @JsonProperty("hypervisor_total_io_size_kbytes")
    public String getHypervisorTotalIoSizeKbytes() {
        return hypervisorTotalIoSizeKbytes;
    }

    @JsonProperty("hypervisor_total_io_size_kbytes")
    public void setHypervisorTotalIoSizeKbytes(String hypervisorTotalIoSizeKbytes) {
        this.hypervisorTotalIoSizeKbytes = hypervisorTotalIoSizeKbytes;
    }

    @JsonProperty("avg_io_latency_usecs")
    public String getAvgIoLatencyUsecs() {
        return avgIoLatencyUsecs;
    }

    @JsonProperty("avg_io_latency_usecs")
    public void setAvgIoLatencyUsecs(String avgIoLatencyUsecs) {
        this.avgIoLatencyUsecs = avgIoLatencyUsecs;
    }

    @JsonProperty("hypervisor_num_read_iops")
    public String getHypervisorNumReadIops() {
        return hypervisorNumReadIops;
    }

    @JsonProperty("hypervisor_num_read_iops")
    public void setHypervisorNumReadIops(String hypervisorNumReadIops) {
        this.hypervisorNumReadIops = hypervisorNumReadIops;
    }

    @JsonProperty("hypervisor_swap_in_rate_kBps")
    public String getHypervisorSwapInRateKBps() {
        return hypervisorSwapInRateKBps;
    }

    @JsonProperty("hypervisor_swap_in_rate_kBps")
    public void setHypervisorSwapInRateKBps(String hypervisorSwapInRateKBps) {
        this.hypervisorSwapInRateKBps = hypervisorSwapInRateKBps;
    }

    @JsonProperty("controller_write_io_bandwidth_kBps")
    public String getControllerWriteIoBandwidthKBps() {
        return controllerWriteIoBandwidthKBps;
    }

    @JsonProperty("controller_write_io_bandwidth_kBps")
    public void setControllerWriteIoBandwidthKBps(String controllerWriteIoBandwidthKBps) {
        this.controllerWriteIoBandwidthKBps = controllerWriteIoBandwidthKBps;
    }

    @JsonProperty("controller_write_io_ppm")
    public String getControllerWriteIoPpm() {
        return controllerWriteIoPpm;
    }

    @JsonProperty("controller_write_io_ppm")
    public void setControllerWriteIoPpm(String controllerWriteIoPpm) {
        this.controllerWriteIoPpm = controllerWriteIoPpm;
    }

    @JsonProperty("controller_user_bytes")
    public String getControllerUserBytes() {
        return controllerUserBytes;
    }

    @JsonProperty("controller_user_bytes")
    public void setControllerUserBytes(String controllerUserBytes) {
        this.controllerUserBytes = controllerUserBytes;
    }

    @JsonProperty("hypervisor_avg_write_io_latency_usecs")
    public String getHypervisorAvgWriteIoLatencyUsecs() {
        return hypervisorAvgWriteIoLatencyUsecs;
    }

    @JsonProperty("hypervisor_avg_write_io_latency_usecs")
    public void setHypervisorAvgWriteIoLatencyUsecs(String hypervisorAvgWriteIoLatencyUsecs) {
        this.hypervisorAvgWriteIoLatencyUsecs = hypervisorAvgWriteIoLatencyUsecs;
    }

    @JsonProperty("hypervisor_num_transmitted_bytes")
    public String getHypervisorNumTransmittedBytes() {
        return hypervisorNumTransmittedBytes;
    }

    @JsonProperty("hypervisor_num_transmitted_bytes")
    public void setHypervisorNumTransmittedBytes(String hypervisorNumTransmittedBytes) {
        this.hypervisorNumTransmittedBytes = hypervisorNumTransmittedBytes;
    }

    @JsonProperty("hypervisor_total_read_io_size_kbytes")
    public String getHypervisorTotalReadIoSizeKbytes() {
        return hypervisorTotalReadIoSizeKbytes;
    }

    @JsonProperty("hypervisor_total_read_io_size_kbytes")
    public void setHypervisorTotalReadIoSizeKbytes(String hypervisorTotalReadIoSizeKbytes) {
        this.hypervisorTotalReadIoSizeKbytes = hypervisorTotalReadIoSizeKbytes;
    }

    @JsonProperty("read_io_bandwidth_kBps")
    public String getReadIoBandwidthKBps() {
        return readIoBandwidthKBps;
    }

    @JsonProperty("read_io_bandwidth_kBps")
    public void setReadIoBandwidthKBps(String readIoBandwidthKBps) {
        this.readIoBandwidthKBps = readIoBandwidthKBps;
    }

    @JsonProperty("guest.memory_swapped_out_bytes")
    public String getGuestMemorySwappedOutBytes() {
        return guestMemorySwappedOutBytes;
    }

    @JsonProperty("guest.memory_swapped_out_bytes")
    public void setGuestMemorySwappedOutBytes(String guestMemorySwappedOutBytes) {
        this.guestMemorySwappedOutBytes = guestMemorySwappedOutBytes;
    }

    @JsonProperty("hypervisor_memory_usage_ppm")
    public String getHypervisorMemoryUsagePpm() {
        return hypervisorMemoryUsagePpm;
    }

    @JsonProperty("hypervisor_memory_usage_ppm")
    public void setHypervisorMemoryUsagePpm(String hypervisorMemoryUsagePpm) {
        this.hypervisorMemoryUsagePpm = hypervisorMemoryUsagePpm;
    }

    @JsonProperty("hypervisor_num_iops")
    public String getHypervisorNumIops() {
        return hypervisorNumIops;
    }

    @JsonProperty("hypervisor_num_iops")
    public void setHypervisorNumIops(String hypervisorNumIops) {
        this.hypervisorNumIops = hypervisorNumIops;
    }

    @JsonProperty("hypervisor_io_bandwidth_kBps")
    public String getHypervisorIoBandwidthKBps() {
        return hypervisorIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_io_bandwidth_kBps")
    public void setHypervisorIoBandwidthKBps(String hypervisorIoBandwidthKBps) {
        this.hypervisorIoBandwidthKBps = hypervisorIoBandwidthKBps;
    }

    @JsonProperty("controller_num_write_iops")
    public String getControllerNumWriteIops() {
        return controllerNumWriteIops;
    }

    @JsonProperty("controller_num_write_iops")
    public void setControllerNumWriteIops(String controllerNumWriteIops) {
        this.controllerNumWriteIops = controllerNumWriteIops;
    }

    @JsonProperty("total_io_time_usecs")
    public String getTotalIoTimeUsecs() {
        return totalIoTimeUsecs;
    }

    @JsonProperty("total_io_time_usecs")
    public void setTotalIoTimeUsecs(String totalIoTimeUsecs) {
        this.totalIoTimeUsecs = totalIoTimeUsecs;
    }

    @JsonProperty("controller_random_io_ppm")
    public String getControllerRandomIoPpm() {
        return controllerRandomIoPpm;
    }

    @JsonProperty("controller_random_io_ppm")
    public void setControllerRandomIoPpm(String controllerRandomIoPpm) {
        this.controllerRandomIoPpm = controllerRandomIoPpm;
    }

    @JsonProperty("controller.storage_tier.das-sata.usage_bytes")
    public String getControllerStorageTierDasSataUsageBytes() {
        return controllerStorageTierDasSataUsageBytes;
    }

    @JsonProperty("controller.storage_tier.das-sata.usage_bytes")
    public void setControllerStorageTierDasSataUsageBytes(String controllerStorageTierDasSataUsageBytes) {
        this.controllerStorageTierDasSataUsageBytes = controllerStorageTierDasSataUsageBytes;
    }

    @JsonProperty("controller_avg_read_io_size_kbytes")
    public String getControllerAvgReadIoSizeKbytes() {
        return controllerAvgReadIoSizeKbytes;
    }

    @JsonProperty("controller_avg_read_io_size_kbytes")
    public void setControllerAvgReadIoSizeKbytes(String controllerAvgReadIoSizeKbytes) {
        this.controllerAvgReadIoSizeKbytes = controllerAvgReadIoSizeKbytes;
    }

    @JsonProperty("hypervisor_swap_out_rate_kBps")
    public String getHypervisorSwapOutRateKBps() {
        return hypervisorSwapOutRateKBps;
    }

    @JsonProperty("hypervisor_swap_out_rate_kBps")
    public void setHypervisorSwapOutRateKBps(String hypervisorSwapOutRateKBps) {
        this.hypervisorSwapOutRateKBps = hypervisorSwapOutRateKBps;
    }

    @JsonProperty("total_transformed_usage_bytes")
    public String getTotalTransformedUsageBytes() {
        return totalTransformedUsageBytes;
    }

    @JsonProperty("total_transformed_usage_bytes")
    public void setTotalTransformedUsageBytes(String totalTransformedUsageBytes) {
        this.totalTransformedUsageBytes = totalTransformedUsageBytes;
    }

    @JsonProperty("avg_write_io_latency_usecs")
    public String getAvgWriteIoLatencyUsecs() {
        return avgWriteIoLatencyUsecs;
    }

    @JsonProperty("avg_write_io_latency_usecs")
    public void setAvgWriteIoLatencyUsecs(String avgWriteIoLatencyUsecs) {
        this.avgWriteIoLatencyUsecs = avgWriteIoLatencyUsecs;
    }

    @JsonProperty("num_read_io")
    public String getNumReadIo() {
        return numReadIo;
    }

    @JsonProperty("num_read_io")
    public void setNumReadIo(String numReadIo) {
        this.numReadIo = numReadIo;
    }

    @JsonProperty("write_io_bandwidth_kBps")
    public String getWriteIoBandwidthKBps() {
        return writeIoBandwidthKBps;
    }

    @JsonProperty("write_io_bandwidth_kBps")
    public void setWriteIoBandwidthKBps(String writeIoBandwidthKBps) {
        this.writeIoBandwidthKBps = writeIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_read_io_bandwidth_kBps")
    public String getHypervisorReadIoBandwidthKBps() {
        return hypervisorReadIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_read_io_bandwidth_kBps")
    public void setHypervisorReadIoBandwidthKBps(String hypervisorReadIoBandwidthKBps) {
        this.hypervisorReadIoBandwidthKBps = hypervisorReadIoBandwidthKBps;
    }

    @JsonProperty("hypervisor_consumed_memory_bytes")
    public String getHypervisorConsumedMemoryBytes() {
        return hypervisorConsumedMemoryBytes;
    }

    @JsonProperty("hypervisor_consumed_memory_bytes")
    public void setHypervisorConsumedMemoryBytes(String hypervisorConsumedMemoryBytes) {
        this.hypervisorConsumedMemoryBytes = hypervisorConsumedMemoryBytes;
    }

    @JsonProperty("random_io_ppm")
    public String getRandomIoPpm() {
        return randomIoPpm;
    }

    @JsonProperty("random_io_ppm")
    public void setRandomIoPpm(String randomIoPpm) {
        this.randomIoPpm = randomIoPpm;
    }

    @JsonProperty("total_untransformed_usage_bytes")
    public String getTotalUntransformedUsageBytes() {
        return totalUntransformedUsageBytes;
    }

    @JsonProperty("total_untransformed_usage_bytes")
    public void setTotalUntransformedUsageBytes(String totalUntransformedUsageBytes) {
        this.totalUntransformedUsageBytes = totalUntransformedUsageBytes;
    }

    @JsonProperty("hypervisor_total_io_time_usecs")
    public String getHypervisorTotalIoTimeUsecs() {
        return hypervisorTotalIoTimeUsecs;
    }

    @JsonProperty("hypervisor_total_io_time_usecs")
    public void setHypervisorTotalIoTimeUsecs(String hypervisorTotalIoTimeUsecs) {
        this.hypervisorTotalIoTimeUsecs = hypervisorTotalIoTimeUsecs;
    }

    @JsonProperty("num_random_io")
    public String getNumRandomIo() {
        return numRandomIo;
    }

    @JsonProperty("num_random_io")
    public void setNumRandomIo(String numRandomIo) {
        this.numRandomIo = numRandomIo;
    }

    @JsonProperty("controller_avg_write_io_size_kbytes")
    public String getControllerAvgWriteIoSizeKbytes() {
        return controllerAvgWriteIoSizeKbytes;
    }

    @JsonProperty("controller_avg_write_io_size_kbytes")
    public void setControllerAvgWriteIoSizeKbytes(String controllerAvgWriteIoSizeKbytes) {
        this.controllerAvgWriteIoSizeKbytes = controllerAvgWriteIoSizeKbytes;
    }

    @JsonProperty("controller_avg_read_io_latency_usecs")
    public String getControllerAvgReadIoLatencyUsecs() {
        return controllerAvgReadIoLatencyUsecs;
    }

    @JsonProperty("controller_avg_read_io_latency_usecs")
    public void setControllerAvgReadIoLatencyUsecs(String controllerAvgReadIoLatencyUsecs) {
        this.controllerAvgReadIoLatencyUsecs = controllerAvgReadIoLatencyUsecs;
    }

    @JsonProperty("controller.storage_tier.das-sata.configured_pinned_bytes")
    public String getControllerStorageTierDasSataConfiguredPinnedBytes() {
        return controllerStorageTierDasSataConfiguredPinnedBytes;
    }

    @JsonProperty("controller.storage_tier.das-sata.configured_pinned_bytes")
    public void setControllerStorageTierDasSataConfiguredPinnedBytes(String controllerStorageTierDasSataConfiguredPinnedBytes) {
        this.controllerStorageTierDasSataConfiguredPinnedBytes = controllerStorageTierDasSataConfiguredPinnedBytes;
    }

    @JsonProperty("num_write_io")
    public String getNumWriteIo() {
        return numWriteIo;
    }

    @JsonProperty("num_write_io")
    public void setNumWriteIo(String numWriteIo) {
        this.numWriteIo = numWriteIo;
    }

    @JsonProperty("total_io_size_kbytes")
    public String getTotalIoSizeKbytes() {
        return totalIoSizeKbytes;
    }

    @JsonProperty("total_io_size_kbytes")
    public void setTotalIoSizeKbytes(String totalIoSizeKbytes) {
        this.totalIoSizeKbytes = totalIoSizeKbytes;
    }

    @JsonProperty("controller.storage_tier.cloud.usage_bytes")
    public String getControllerStorageTierCloudUsageBytes() {
        return controllerStorageTierCloudUsageBytes;
    }

    @JsonProperty("controller.storage_tier.cloud.usage_bytes")
    public void setControllerStorageTierCloudUsageBytes(String controllerStorageTierCloudUsageBytes) {
        this.controllerStorageTierCloudUsageBytes = controllerStorageTierCloudUsageBytes;
    }

    @JsonProperty("io_bandwidth_kBps")
    public String getIoBandwidthKBps() {
        return ioBandwidthKBps;
    }

    @JsonProperty("io_bandwidth_kBps")
    public void setIoBandwidthKBps(String ioBandwidthKBps) {
        this.ioBandwidthKBps = ioBandwidthKBps;
    }

    @JsonProperty("controller_timespan_usecs")
    public String getControllerTimespanUsecs() {
        return controllerTimespanUsecs;
    }

    @JsonProperty("controller_timespan_usecs")
    public void setControllerTimespanUsecs(String controllerTimespanUsecs) {
        this.controllerTimespanUsecs = controllerTimespanUsecs;
    }

    @JsonProperty("num_seq_io")
    public String getNumSeqIo() {
        return numSeqIo;
    }

    @JsonProperty("num_seq_io")
    public void setNumSeqIo(String numSeqIo) {
        this.numSeqIo = numSeqIo;
    }

    @JsonProperty("seq_io_ppm")
    public String getSeqIoPpm() {
        return seqIoPpm;
    }

    @JsonProperty("seq_io_ppm")
    public void setSeqIoPpm(String seqIoPpm) {
        this.seqIoPpm = seqIoPpm;
    }

    @JsonProperty("write_io_ppm")
    public String getWriteIoPpm() {
        return writeIoPpm;
    }

    @JsonProperty("write_io_ppm")
    public void setWriteIoPpm(String writeIoPpm) {
        this.writeIoPpm = writeIoPpm;
    }

    @JsonProperty("controller_avg_write_io_latency_usecs")
    public String getControllerAvgWriteIoLatencyUsecs() {
        return controllerAvgWriteIoLatencyUsecs;
    }

    @JsonProperty("controller_avg_write_io_latency_usecs")
    public void setControllerAvgWriteIoLatencyUsecs(String controllerAvgWriteIoLatencyUsecs) {
        this.controllerAvgWriteIoLatencyUsecs = controllerAvgWriteIoLatencyUsecs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Stats.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("hypervisorAvgIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.hypervisorAvgIoLatencyUsecs == null)?"<null>":this.hypervisorAvgIoLatencyUsecs));
        sb.append(',');
        sb.append("numReadIops");
        sb.append('=');
        sb.append(((this.numReadIops == null)?"<null>":this.numReadIops));
        sb.append(',');
        sb.append("hypervisorWriteIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.hypervisorWriteIoBandwidthKBps == null)?"<null>":this.hypervisorWriteIoBandwidthKBps));
        sb.append(',');
        sb.append("timespanUsecs");
        sb.append('=');
        sb.append(((this.timespanUsecs == null)?"<null>":this.timespanUsecs));
        sb.append(',');
        sb.append("controllerNumReadIops");
        sb.append('=');
        sb.append(((this.controllerNumReadIops == null)?"<null>":this.controllerNumReadIops));
        sb.append(',');
        sb.append("controllerStorageTierSsdUsageBytes");
        sb.append('=');
        sb.append(((this.controllerStorageTierSsdUsageBytes == null)?"<null>":this.controllerStorageTierSsdUsageBytes));
        sb.append(',');
        sb.append("readIoPpm");
        sb.append('=');
        sb.append(((this.readIoPpm == null)?"<null>":this.readIoPpm));
        sb.append(',');
        sb.append("controllerNumIops");
        sb.append('=');
        sb.append(((this.controllerNumIops == null)?"<null>":this.controllerNumIops));
        sb.append(',');
        sb.append("hypervisorMemoryAssignedBytes");
        sb.append('=');
        sb.append(((this.hypervisorMemoryAssignedBytes == null)?"<null>":this.hypervisorMemoryAssignedBytes));
        sb.append(',');
        sb.append("totalReadIoTimeUsecs");
        sb.append('=');
        sb.append(((this.totalReadIoTimeUsecs == null)?"<null>":this.totalReadIoTimeUsecs));
        sb.append(',');
        sb.append("controllerTotalReadIoTimeUsecs");
        sb.append('=');
        sb.append(((this.controllerTotalReadIoTimeUsecs == null)?"<null>":this.controllerTotalReadIoTimeUsecs));
        sb.append(',');
        sb.append("controllerStorageTierSsdConfiguredPinnedBytes");
        sb.append('=');
        sb.append(((this.controllerStorageTierSsdConfiguredPinnedBytes == null)?"<null>":this.controllerStorageTierSsdConfiguredPinnedBytes));
        sb.append(',');
        sb.append("hypervisorNumIo");
        sb.append('=');
        sb.append(((this.hypervisorNumIo == null)?"<null>":this.hypervisorNumIo));
        sb.append(',');
        sb.append("controllerTotalTransformedUsageBytes");
        sb.append('=');
        sb.append(((this.controllerTotalTransformedUsageBytes == null)?"<null>":this.controllerTotalTransformedUsageBytes));
        sb.append(',');
        sb.append("hypervisorCpuUsagePpm");
        sb.append('=');
        sb.append(((this.hypervisorCpuUsagePpm == null)?"<null>":this.hypervisorCpuUsagePpm));
        sb.append(',');
        sb.append("controllerNumWriteIo");
        sb.append('=');
        sb.append(((this.controllerNumWriteIo == null)?"<null>":this.controllerNumWriteIo));
        sb.append(',');
        sb.append("avgReadIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.avgReadIoLatencyUsecs == null)?"<null>":this.avgReadIoLatencyUsecs));
        sb.append(',');
        sb.append("guestMemorySwappedInBytes");
        sb.append('=');
        sb.append(((this.guestMemorySwappedInBytes == null)?"<null>":this.guestMemorySwappedInBytes));
        sb.append(',');
        sb.append("controllerTotalIoTimeUsecs");
        sb.append('=');
        sb.append(((this.controllerTotalIoTimeUsecs == null)?"<null>":this.controllerTotalIoTimeUsecs));
        sb.append(',');
        sb.append("memoryUsagePpm");
        sb.append('=');
        sb.append(((this.memoryUsagePpm == null)?"<null>":this.memoryUsagePpm));
        sb.append(',');
        sb.append("controllerTotalReadIoSizeKbytes");
        sb.append('=');
        sb.append(((this.controllerTotalReadIoSizeKbytes == null)?"<null>":this.controllerTotalReadIoSizeKbytes));
        sb.append(',');
        sb.append("controllerNumSeqIo");
        sb.append('=');
        sb.append(((this.controllerNumSeqIo == null)?"<null>":this.controllerNumSeqIo));
        sb.append(',');
        sb.append("controllerReadIoPpm");
        sb.append('=');
        sb.append(((this.controllerReadIoPpm == null)?"<null>":this.controllerReadIoPpm));
        sb.append(',');
        sb.append("controllerTotalIoSizeKbytes");
        sb.append('=');
        sb.append(((this.controllerTotalIoSizeKbytes == null)?"<null>":this.controllerTotalIoSizeKbytes));
        sb.append(',');
        sb.append("hypervisorCpuReadyTimePpm");
        sb.append('=');
        sb.append(((this.hypervisorCpuReadyTimePpm == null)?"<null>":this.hypervisorCpuReadyTimePpm));
        sb.append(',');
        sb.append("controllerNumIo");
        sb.append('=');
        sb.append(((this.controllerNumIo == null)?"<null>":this.controllerNumIo));
        sb.append(',');
        sb.append("hypervisorAvgReadIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.hypervisorAvgReadIoLatencyUsecs == null)?"<null>":this.hypervisorAvgReadIoLatencyUsecs));
        sb.append(',');
        sb.append("numWriteIops");
        sb.append('=');
        sb.append(((this.numWriteIops == null)?"<null>":this.numWriteIops));
        sb.append(',');
        sb.append("controllerNumRandomIo");
        sb.append('=');
        sb.append(((this.controllerNumRandomIo == null)?"<null>":this.controllerNumRandomIo));
        sb.append(',');
        sb.append("numIops");
        sb.append('=');
        sb.append(((this.numIops == null)?"<null>":this.numIops));
        sb.append(',');
        sb.append("guestMemoryUsagePpm");
        sb.append('=');
        sb.append(((this.guestMemoryUsagePpm == null)?"<null>":this.guestMemoryUsagePpm));
        sb.append(',');
        sb.append("hypervisorNumReadIo");
        sb.append('=');
        sb.append(((this.hypervisorNumReadIo == null)?"<null>":this.hypervisorNumReadIo));
        sb.append(',');
        sb.append("hypervisorTotalReadIoTimeUsecs");
        sb.append('=');
        sb.append(((this.hypervisorTotalReadIoTimeUsecs == null)?"<null>":this.hypervisorTotalReadIoTimeUsecs));
        sb.append(',');
        sb.append("controllerAvgIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.controllerAvgIoLatencyUsecs == null)?"<null>":this.controllerAvgIoLatencyUsecs));
        sb.append(',');
        sb.append("numIo");
        sb.append('=');
        sb.append(((this.numIo == null)?"<null>":this.numIo));
        sb.append(',');
        sb.append("controllerNumReadIo");
        sb.append('=');
        sb.append(((this.controllerNumReadIo == null)?"<null>":this.controllerNumReadIo));
        sb.append(',');
        sb.append("hypervisorNumWriteIo");
        sb.append('=');
        sb.append(((this.hypervisorNumWriteIo == null)?"<null>":this.hypervisorNumWriteIo));
        sb.append(',');
        sb.append("controllerSeqIoPpm");
        sb.append('=');
        sb.append(((this.controllerSeqIoPpm == null)?"<null>":this.controllerSeqIoPpm));
        sb.append(',');
        sb.append("guestMemoryUsageBytes");
        sb.append('=');
        sb.append(((this.guestMemoryUsageBytes == null)?"<null>":this.guestMemoryUsageBytes));
        sb.append(',');
        sb.append("controllerReadIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.controllerReadIoBandwidthKBps == null)?"<null>":this.controllerReadIoBandwidthKBps));
        sb.append(',');
        sb.append("controllerIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.controllerIoBandwidthKBps == null)?"<null>":this.controllerIoBandwidthKBps));
        sb.append(',');
        sb.append("hypervisorNumReceivedBytes");
        sb.append('=');
        sb.append(((this.hypervisorNumReceivedBytes == null)?"<null>":this.hypervisorNumReceivedBytes));
        sb.append(',');
        sb.append("hypervisorTimespanUsecs");
        sb.append('=');
        sb.append(((this.hypervisorTimespanUsecs == null)?"<null>":this.hypervisorTimespanUsecs));
        sb.append(',');
        sb.append("hypervisorNumWriteIops");
        sb.append('=');
        sb.append(((this.hypervisorNumWriteIops == null)?"<null>":this.hypervisorNumWriteIops));
        sb.append(',');
        sb.append("totalReadIoSizeKbytes");
        sb.append('=');
        sb.append(((this.totalReadIoSizeKbytes == null)?"<null>":this.totalReadIoSizeKbytes));
        sb.append(',');
        sb.append("hypervisorTotalIoSizeKbytes");
        sb.append('=');
        sb.append(((this.hypervisorTotalIoSizeKbytes == null)?"<null>":this.hypervisorTotalIoSizeKbytes));
        sb.append(',');
        sb.append("avgIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.avgIoLatencyUsecs == null)?"<null>":this.avgIoLatencyUsecs));
        sb.append(',');
        sb.append("hypervisorNumReadIops");
        sb.append('=');
        sb.append(((this.hypervisorNumReadIops == null)?"<null>":this.hypervisorNumReadIops));
        sb.append(',');
        sb.append("hypervisorSwapInRateKBps");
        sb.append('=');
        sb.append(((this.hypervisorSwapInRateKBps == null)?"<null>":this.hypervisorSwapInRateKBps));
        sb.append(',');
        sb.append("controllerWriteIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.controllerWriteIoBandwidthKBps == null)?"<null>":this.controllerWriteIoBandwidthKBps));
        sb.append(',');
        sb.append("controllerWriteIoPpm");
        sb.append('=');
        sb.append(((this.controllerWriteIoPpm == null)?"<null>":this.controllerWriteIoPpm));
        sb.append(',');
        sb.append("controllerUserBytes");
        sb.append('=');
        sb.append(((this.controllerUserBytes == null)?"<null>":this.controllerUserBytes));
        sb.append(',');
        sb.append("hypervisorAvgWriteIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.hypervisorAvgWriteIoLatencyUsecs == null)?"<null>":this.hypervisorAvgWriteIoLatencyUsecs));
        sb.append(',');
        sb.append("hypervisorNumTransmittedBytes");
        sb.append('=');
        sb.append(((this.hypervisorNumTransmittedBytes == null)?"<null>":this.hypervisorNumTransmittedBytes));
        sb.append(',');
        sb.append("hypervisorTotalReadIoSizeKbytes");
        sb.append('=');
        sb.append(((this.hypervisorTotalReadIoSizeKbytes == null)?"<null>":this.hypervisorTotalReadIoSizeKbytes));
        sb.append(',');
        sb.append("readIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.readIoBandwidthKBps == null)?"<null>":this.readIoBandwidthKBps));
        sb.append(',');
        sb.append("guestMemorySwappedOutBytes");
        sb.append('=');
        sb.append(((this.guestMemorySwappedOutBytes == null)?"<null>":this.guestMemorySwappedOutBytes));
        sb.append(',');
        sb.append("hypervisorMemoryUsagePpm");
        sb.append('=');
        sb.append(((this.hypervisorMemoryUsagePpm == null)?"<null>":this.hypervisorMemoryUsagePpm));
        sb.append(',');
        sb.append("hypervisorNumIops");
        sb.append('=');
        sb.append(((this.hypervisorNumIops == null)?"<null>":this.hypervisorNumIops));
        sb.append(',');
        sb.append("hypervisorIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.hypervisorIoBandwidthKBps == null)?"<null>":this.hypervisorIoBandwidthKBps));
        sb.append(',');
        sb.append("controllerNumWriteIops");
        sb.append('=');
        sb.append(((this.controllerNumWriteIops == null)?"<null>":this.controllerNumWriteIops));
        sb.append(',');
        sb.append("totalIoTimeUsecs");
        sb.append('=');
        sb.append(((this.totalIoTimeUsecs == null)?"<null>":this.totalIoTimeUsecs));
        sb.append(',');
        sb.append("controllerRandomIoPpm");
        sb.append('=');
        sb.append(((this.controllerRandomIoPpm == null)?"<null>":this.controllerRandomIoPpm));
        sb.append(',');
        sb.append("controllerStorageTierDasSataUsageBytes");
        sb.append('=');
        sb.append(((this.controllerStorageTierDasSataUsageBytes == null)?"<null>":this.controllerStorageTierDasSataUsageBytes));
        sb.append(',');
        sb.append("controllerAvgReadIoSizeKbytes");
        sb.append('=');
        sb.append(((this.controllerAvgReadIoSizeKbytes == null)?"<null>":this.controllerAvgReadIoSizeKbytes));
        sb.append(',');
        sb.append("hypervisorSwapOutRateKBps");
        sb.append('=');
        sb.append(((this.hypervisorSwapOutRateKBps == null)?"<null>":this.hypervisorSwapOutRateKBps));
        sb.append(',');
        sb.append("totalTransformedUsageBytes");
        sb.append('=');
        sb.append(((this.totalTransformedUsageBytes == null)?"<null>":this.totalTransformedUsageBytes));
        sb.append(',');
        sb.append("avgWriteIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.avgWriteIoLatencyUsecs == null)?"<null>":this.avgWriteIoLatencyUsecs));
        sb.append(',');
        sb.append("numReadIo");
        sb.append('=');
        sb.append(((this.numReadIo == null)?"<null>":this.numReadIo));
        sb.append(',');
        sb.append("writeIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.writeIoBandwidthKBps == null)?"<null>":this.writeIoBandwidthKBps));
        sb.append(',');
        sb.append("hypervisorReadIoBandwidthKBps");
        sb.append('=');
        sb.append(((this.hypervisorReadIoBandwidthKBps == null)?"<null>":this.hypervisorReadIoBandwidthKBps));
        sb.append(',');
        sb.append("hypervisorConsumedMemoryBytes");
        sb.append('=');
        sb.append(((this.hypervisorConsumedMemoryBytes == null)?"<null>":this.hypervisorConsumedMemoryBytes));
        sb.append(',');
        sb.append("randomIoPpm");
        sb.append('=');
        sb.append(((this.randomIoPpm == null)?"<null>":this.randomIoPpm));
        sb.append(',');
        sb.append("totalUntransformedUsageBytes");
        sb.append('=');
        sb.append(((this.totalUntransformedUsageBytes == null)?"<null>":this.totalUntransformedUsageBytes));
        sb.append(',');
        sb.append("hypervisorTotalIoTimeUsecs");
        sb.append('=');
        sb.append(((this.hypervisorTotalIoTimeUsecs == null)?"<null>":this.hypervisorTotalIoTimeUsecs));
        sb.append(',');
        sb.append("numRandomIo");
        sb.append('=');
        sb.append(((this.numRandomIo == null)?"<null>":this.numRandomIo));
        sb.append(',');
        sb.append("controllerAvgWriteIoSizeKbytes");
        sb.append('=');
        sb.append(((this.controllerAvgWriteIoSizeKbytes == null)?"<null>":this.controllerAvgWriteIoSizeKbytes));
        sb.append(',');
        sb.append("controllerAvgReadIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.controllerAvgReadIoLatencyUsecs == null)?"<null>":this.controllerAvgReadIoLatencyUsecs));
        sb.append(',');
        sb.append("controllerStorageTierDasSataConfiguredPinnedBytes");
        sb.append('=');
        sb.append(((this.controllerStorageTierDasSataConfiguredPinnedBytes == null)?"<null>":this.controllerStorageTierDasSataConfiguredPinnedBytes));
        sb.append(',');
        sb.append("numWriteIo");
        sb.append('=');
        sb.append(((this.numWriteIo == null)?"<null>":this.numWriteIo));
        sb.append(',');
        sb.append("totalIoSizeKbytes");
        sb.append('=');
        sb.append(((this.totalIoSizeKbytes == null)?"<null>":this.totalIoSizeKbytes));
        sb.append(',');
        sb.append("controllerStorageTierCloudUsageBytes");
        sb.append('=');
        sb.append(((this.controllerStorageTierCloudUsageBytes == null)?"<null>":this.controllerStorageTierCloudUsageBytes));
        sb.append(',');
        sb.append("ioBandwidthKBps");
        sb.append('=');
        sb.append(((this.ioBandwidthKBps == null)?"<null>":this.ioBandwidthKBps));
        sb.append(',');
        sb.append("controllerTimespanUsecs");
        sb.append('=');
        sb.append(((this.controllerTimespanUsecs == null)?"<null>":this.controllerTimespanUsecs));
        sb.append(',');
        sb.append("numSeqIo");
        sb.append('=');
        sb.append(((this.numSeqIo == null)?"<null>":this.numSeqIo));
        sb.append(',');
        sb.append("seqIoPpm");
        sb.append('=');
        sb.append(((this.seqIoPpm == null)?"<null>":this.seqIoPpm));
        sb.append(',');
        sb.append("writeIoPpm");
        sb.append('=');
        sb.append(((this.writeIoPpm == null)?"<null>":this.writeIoPpm));
        sb.append(',');
        sb.append("controllerAvgWriteIoLatencyUsecs");
        sb.append('=');
        sb.append(((this.controllerAvgWriteIoLatencyUsecs == null)?"<null>":this.controllerAvgWriteIoLatencyUsecs));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.numReadIo == null)? 0 :this.numReadIo.hashCode()));
        result = ((result* 31)+((this.controllerStorageTierCloudUsageBytes == null)? 0 :this.controllerStorageTierCloudUsageBytes.hashCode()));
        result = ((result* 31)+((this.hypervisorSwapInRateKBps == null)? 0 :this.hypervisorSwapInRateKBps.hashCode()));
        result = ((result* 31)+((this.hypervisorTotalIoSizeKbytes == null)? 0 :this.hypervisorTotalIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.hypervisorCpuReadyTimePpm == null)? 0 :this.hypervisorCpuReadyTimePpm.hashCode()));
        result = ((result* 31)+((this.controllerSeqIoPpm == null)? 0 :this.controllerSeqIoPpm.hashCode()));
        result = ((result* 31)+((this.numWriteIo == null)? 0 :this.numWriteIo.hashCode()));
        result = ((result* 31)+((this.numIo == null)? 0 :this.numIo.hashCode()));
        result = ((result* 31)+((this.controllerAvgReadIoLatencyUsecs == null)? 0 :this.controllerAvgReadIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.controllerTimespanUsecs == null)? 0 :this.controllerTimespanUsecs.hashCode()));
        result = ((result* 31)+((this.guestMemorySwappedInBytes == null)? 0 :this.guestMemorySwappedInBytes.hashCode()));
        result = ((result* 31)+((this.controllerNumSeqIo == null)? 0 :this.controllerNumSeqIo.hashCode()));
        result = ((result* 31)+((this.totalIoSizeKbytes == null)? 0 :this.totalIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.controllerStorageTierDasSataUsageBytes == null)? 0 :this.controllerStorageTierDasSataUsageBytes.hashCode()));
        result = ((result* 31)+((this.hypervisorIoBandwidthKBps == null)? 0 :this.hypervisorIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.hypervisorReadIoBandwidthKBps == null)? 0 :this.hypervisorReadIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.controllerNumRandomIo == null)? 0 :this.controllerNumRandomIo.hashCode()));
        result = ((result* 31)+((this.writeIoPpm == null)? 0 :this.writeIoPpm.hashCode()));
        result = ((result* 31)+((this.readIoPpm == null)? 0 :this.readIoPpm.hashCode()));
        result = ((result* 31)+((this.hypervisorTotalReadIoTimeUsecs == null)? 0 :this.hypervisorTotalReadIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.numReadIops == null)? 0 :this.numReadIops.hashCode()));
        result = ((result* 31)+((this.controllerStorageTierSsdConfiguredPinnedBytes == null)? 0 :this.controllerStorageTierSsdConfiguredPinnedBytes.hashCode()));
        result = ((result* 31)+((this.controllerNumWriteIops == null)? 0 :this.controllerNumWriteIops.hashCode()));
        result = ((result* 31)+((this.totalReadIoTimeUsecs == null)? 0 :this.totalReadIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.memoryUsagePpm == null)? 0 :this.memoryUsagePpm.hashCode()));
        result = ((result* 31)+((this.guestMemoryUsagePpm == null)? 0 :this.guestMemoryUsagePpm.hashCode()));
        result = ((result* 31)+((this.controllerReadIoPpm == null)? 0 :this.controllerReadIoPpm.hashCode()));
        result = ((result* 31)+((this.controllerTotalIoTimeUsecs == null)? 0 :this.controllerTotalIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.controllerIoBandwidthKBps == null)? 0 :this.controllerIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.hypervisorTimespanUsecs == null)? 0 :this.hypervisorTimespanUsecs.hashCode()));
        result = ((result* 31)+((this.hypervisorNumTransmittedBytes == null)? 0 :this.hypervisorNumTransmittedBytes.hashCode()));
        result = ((result* 31)+((this.controllerNumIo == null)? 0 :this.controllerNumIo.hashCode()));
        result = ((result* 31)+((this.controllerWriteIoBandwidthKBps == null)? 0 :this.controllerWriteIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.controllerWriteIoPpm == null)? 0 :this.controllerWriteIoPpm.hashCode()));
        result = ((result* 31)+((this.controllerStorageTierSsdUsageBytes == null)? 0 :this.controllerStorageTierSsdUsageBytes.hashCode()));
        result = ((result* 31)+((this.hypervisorNumWriteIops == null)? 0 :this.hypervisorNumWriteIops.hashCode()));
        result = ((result* 31)+((this.hypervisorTotalReadIoSizeKbytes == null)? 0 :this.hypervisorTotalReadIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.controllerNumReadIops == null)? 0 :this.controllerNumReadIops.hashCode()));
        result = ((result* 31)+((this.totalReadIoSizeKbytes == null)? 0 :this.totalReadIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.readIoBandwidthKBps == null)? 0 :this.readIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.controllerTotalReadIoSizeKbytes == null)? 0 :this.controllerTotalReadIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.writeIoBandwidthKBps == null)? 0 :this.writeIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.seqIoPpm == null)? 0 :this.seqIoPpm.hashCode()));
        result = ((result* 31)+((this.hypervisorTotalIoTimeUsecs == null)? 0 :this.hypervisorTotalIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.hypervisorNumWriteIo == null)? 0 :this.hypervisorNumWriteIo.hashCode()));
        result = ((result* 31)+((this.guestMemoryUsageBytes == null)? 0 :this.guestMemoryUsageBytes.hashCode()));
        result = ((result* 31)+((this.hypervisorNumReadIops == null)? 0 :this.hypervisorNumReadIops.hashCode()));
        result = ((result* 31)+((this.numSeqIo == null)? 0 :this.numSeqIo.hashCode()));
        result = ((result* 31)+((this.hypervisorCpuUsagePpm == null)? 0 :this.hypervisorCpuUsagePpm.hashCode()));
        result = ((result* 31)+((this.hypervisorAvgWriteIoLatencyUsecs == null)? 0 :this.hypervisorAvgWriteIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.hypervisorConsumedMemoryBytes == null)? 0 :this.hypervisorConsumedMemoryBytes.hashCode()));
        result = ((result* 31)+((this.timespanUsecs == null)? 0 :this.timespanUsecs.hashCode()));
        result = ((result* 31)+((this.controllerAvgWriteIoLatencyUsecs == null)? 0 :this.controllerAvgWriteIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.hypervisorWriteIoBandwidthKBps == null)? 0 :this.hypervisorWriteIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.controllerNumIops == null)? 0 :this.controllerNumIops.hashCode()));
        result = ((result* 31)+((this.hypervisorMemoryAssignedBytes == null)? 0 :this.hypervisorMemoryAssignedBytes.hashCode()));
        result = ((result* 31)+((this.controllerTotalReadIoTimeUsecs == null)? 0 :this.controllerTotalReadIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.numRandomIo == null)? 0 :this.numRandomIo.hashCode()));
        result = ((result* 31)+((this.controllerTotalTransformedUsageBytes == null)? 0 :this.controllerTotalTransformedUsageBytes.hashCode()));
        result = ((result* 31)+((this.avgIoLatencyUsecs == null)? 0 :this.avgIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.hypervisorNumReceivedBytes == null)? 0 :this.hypervisorNumReceivedBytes.hashCode()));
        result = ((result* 31)+((this.hypervisorAvgIoLatencyUsecs == null)? 0 :this.hypervisorAvgIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.controllerAvgWriteIoSizeKbytes == null)? 0 :this.controllerAvgWriteIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.hypervisorMemoryUsagePpm == null)? 0 :this.hypervisorMemoryUsagePpm.hashCode()));
        result = ((result* 31)+((this.ioBandwidthKBps == null)? 0 :this.ioBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.hypervisorNumIo == null)? 0 :this.hypervisorNumIo.hashCode()));
        result = ((result* 31)+((this.controllerRandomIoPpm == null)? 0 :this.controllerRandomIoPpm.hashCode()));
        result = ((result* 31)+((this.guestMemorySwappedOutBytes == null)? 0 :this.guestMemorySwappedOutBytes.hashCode()));
        result = ((result* 31)+((this.controllerAvgReadIoSizeKbytes == null)? 0 :this.controllerAvgReadIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.controllerReadIoBandwidthKBps == null)? 0 :this.controllerReadIoBandwidthKBps.hashCode()));
        result = ((result* 31)+((this.hypervisorNumIops == null)? 0 :this.hypervisorNumIops.hashCode()));
        result = ((result* 31)+((this.controllerTotalIoSizeKbytes == null)? 0 :this.controllerTotalIoSizeKbytes.hashCode()));
        result = ((result* 31)+((this.avgReadIoLatencyUsecs == null)? 0 :this.avgReadIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.controllerNumReadIo == null)? 0 :this.controllerNumReadIo.hashCode()));
        result = ((result* 31)+((this.hypervisorSwapOutRateKBps == null)? 0 :this.hypervisorSwapOutRateKBps.hashCode()));
        result = ((result* 31)+((this.randomIoPpm == null)? 0 :this.randomIoPpm.hashCode()));
        result = ((result* 31)+((this.avgWriteIoLatencyUsecs == null)? 0 :this.avgWriteIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.controllerAvgIoLatencyUsecs == null)? 0 :this.controllerAvgIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.totalUntransformedUsageBytes == null)? 0 :this.totalUntransformedUsageBytes.hashCode()));
        result = ((result* 31)+((this.totalIoTimeUsecs == null)? 0 :this.totalIoTimeUsecs.hashCode()));
        result = ((result* 31)+((this.numWriteIops == null)? 0 :this.numWriteIops.hashCode()));
        result = ((result* 31)+((this.numIops == null)? 0 :this.numIops.hashCode()));
        result = ((result* 31)+((this.hypervisorAvgReadIoLatencyUsecs == null)? 0 :this.hypervisorAvgReadIoLatencyUsecs.hashCode()));
        result = ((result* 31)+((this.totalTransformedUsageBytes == null)? 0 :this.totalTransformedUsageBytes.hashCode()));
        result = ((result* 31)+((this.controllerNumWriteIo == null)? 0 :this.controllerNumWriteIo.hashCode()));
        result = ((result* 31)+((this.hypervisorNumReadIo == null)? 0 :this.hypervisorNumReadIo.hashCode()));
        result = ((result* 31)+((this.controllerUserBytes == null)? 0 :this.controllerUserBytes.hashCode()));
        result = ((result* 31)+((this.controllerStorageTierDasSataConfiguredPinnedBytes == null)? 0 :this.controllerStorageTierDasSataConfiguredPinnedBytes.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stats) == false) {
            return false;
        }
        Stats rhs = ((Stats) other);
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.numReadIo == rhs.numReadIo)||((this.numReadIo!= null)&&this.numReadIo.equals(rhs.numReadIo)))&&((this.controllerStorageTierCloudUsageBytes == rhs.controllerStorageTierCloudUsageBytes)||((this.controllerStorageTierCloudUsageBytes!= null)&&this.controllerStorageTierCloudUsageBytes.equals(rhs.controllerStorageTierCloudUsageBytes))))&&((this.hypervisorSwapInRateKBps == rhs.hypervisorSwapInRateKBps)||((this.hypervisorSwapInRateKBps!= null)&&this.hypervisorSwapInRateKBps.equals(rhs.hypervisorSwapInRateKBps))))&&((this.hypervisorTotalIoSizeKbytes == rhs.hypervisorTotalIoSizeKbytes)||((this.hypervisorTotalIoSizeKbytes!= null)&&this.hypervisorTotalIoSizeKbytes.equals(rhs.hypervisorTotalIoSizeKbytes))))&&((this.hypervisorCpuReadyTimePpm == rhs.hypervisorCpuReadyTimePpm)||((this.hypervisorCpuReadyTimePpm!= null)&&this.hypervisorCpuReadyTimePpm.equals(rhs.hypervisorCpuReadyTimePpm))))&&((this.controllerSeqIoPpm == rhs.controllerSeqIoPpm)||((this.controllerSeqIoPpm!= null)&&this.controllerSeqIoPpm.equals(rhs.controllerSeqIoPpm))))&&((this.numWriteIo == rhs.numWriteIo)||((this.numWriteIo!= null)&&this.numWriteIo.equals(rhs.numWriteIo))))&&((this.numIo == rhs.numIo)||((this.numIo!= null)&&this.numIo.equals(rhs.numIo))))&&((this.controllerAvgReadIoLatencyUsecs == rhs.controllerAvgReadIoLatencyUsecs)||((this.controllerAvgReadIoLatencyUsecs!= null)&&this.controllerAvgReadIoLatencyUsecs.equals(rhs.controllerAvgReadIoLatencyUsecs))))&&((this.controllerTimespanUsecs == rhs.controllerTimespanUsecs)||((this.controllerTimespanUsecs!= null)&&this.controllerTimespanUsecs.equals(rhs.controllerTimespanUsecs))))&&((this.guestMemorySwappedInBytes == rhs.guestMemorySwappedInBytes)||((this.guestMemorySwappedInBytes!= null)&&this.guestMemorySwappedInBytes.equals(rhs.guestMemorySwappedInBytes))))&&((this.controllerNumSeqIo == rhs.controllerNumSeqIo)||((this.controllerNumSeqIo!= null)&&this.controllerNumSeqIo.equals(rhs.controllerNumSeqIo))))&&((this.totalIoSizeKbytes == rhs.totalIoSizeKbytes)||((this.totalIoSizeKbytes!= null)&&this.totalIoSizeKbytes.equals(rhs.totalIoSizeKbytes))))&&((this.controllerStorageTierDasSataUsageBytes == rhs.controllerStorageTierDasSataUsageBytes)||((this.controllerStorageTierDasSataUsageBytes!= null)&&this.controllerStorageTierDasSataUsageBytes.equals(rhs.controllerStorageTierDasSataUsageBytes))))&&((this.hypervisorIoBandwidthKBps == rhs.hypervisorIoBandwidthKBps)||((this.hypervisorIoBandwidthKBps!= null)&&this.hypervisorIoBandwidthKBps.equals(rhs.hypervisorIoBandwidthKBps))))&&((this.hypervisorReadIoBandwidthKBps == rhs.hypervisorReadIoBandwidthKBps)||((this.hypervisorReadIoBandwidthKBps!= null)&&this.hypervisorReadIoBandwidthKBps.equals(rhs.hypervisorReadIoBandwidthKBps))))&&((this.controllerNumRandomIo == rhs.controllerNumRandomIo)||((this.controllerNumRandomIo!= null)&&this.controllerNumRandomIo.equals(rhs.controllerNumRandomIo))))&&((this.writeIoPpm == rhs.writeIoPpm)||((this.writeIoPpm!= null)&&this.writeIoPpm.equals(rhs.writeIoPpm))))&&((this.readIoPpm == rhs.readIoPpm)||((this.readIoPpm!= null)&&this.readIoPpm.equals(rhs.readIoPpm))))&&((this.hypervisorTotalReadIoTimeUsecs == rhs.hypervisorTotalReadIoTimeUsecs)||((this.hypervisorTotalReadIoTimeUsecs!= null)&&this.hypervisorTotalReadIoTimeUsecs.equals(rhs.hypervisorTotalReadIoTimeUsecs))))&&((this.numReadIops == rhs.numReadIops)||((this.numReadIops!= null)&&this.numReadIops.equals(rhs.numReadIops))))&&((this.controllerStorageTierSsdConfiguredPinnedBytes == rhs.controllerStorageTierSsdConfiguredPinnedBytes)||((this.controllerStorageTierSsdConfiguredPinnedBytes!= null)&&this.controllerStorageTierSsdConfiguredPinnedBytes.equals(rhs.controllerStorageTierSsdConfiguredPinnedBytes))))&&((this.controllerNumWriteIops == rhs.controllerNumWriteIops)||((this.controllerNumWriteIops!= null)&&this.controllerNumWriteIops.equals(rhs.controllerNumWriteIops))))&&((this.totalReadIoTimeUsecs == rhs.totalReadIoTimeUsecs)||((this.totalReadIoTimeUsecs!= null)&&this.totalReadIoTimeUsecs.equals(rhs.totalReadIoTimeUsecs))))&&((this.memoryUsagePpm == rhs.memoryUsagePpm)||((this.memoryUsagePpm!= null)&&this.memoryUsagePpm.equals(rhs.memoryUsagePpm))))&&((this.guestMemoryUsagePpm == rhs.guestMemoryUsagePpm)||((this.guestMemoryUsagePpm!= null)&&this.guestMemoryUsagePpm.equals(rhs.guestMemoryUsagePpm))))&&((this.controllerReadIoPpm == rhs.controllerReadIoPpm)||((this.controllerReadIoPpm!= null)&&this.controllerReadIoPpm.equals(rhs.controllerReadIoPpm))))&&((this.controllerTotalIoTimeUsecs == rhs.controllerTotalIoTimeUsecs)||((this.controllerTotalIoTimeUsecs!= null)&&this.controllerTotalIoTimeUsecs.equals(rhs.controllerTotalIoTimeUsecs))))&&((this.controllerIoBandwidthKBps == rhs.controllerIoBandwidthKBps)||((this.controllerIoBandwidthKBps!= null)&&this.controllerIoBandwidthKBps.equals(rhs.controllerIoBandwidthKBps))))&&((this.hypervisorTimespanUsecs == rhs.hypervisorTimespanUsecs)||((this.hypervisorTimespanUsecs!= null)&&this.hypervisorTimespanUsecs.equals(rhs.hypervisorTimespanUsecs))))&&((this.hypervisorNumTransmittedBytes == rhs.hypervisorNumTransmittedBytes)||((this.hypervisorNumTransmittedBytes!= null)&&this.hypervisorNumTransmittedBytes.equals(rhs.hypervisorNumTransmittedBytes))))&&((this.controllerNumIo == rhs.controllerNumIo)||((this.controllerNumIo!= null)&&this.controllerNumIo.equals(rhs.controllerNumIo))))&&((this.controllerWriteIoBandwidthKBps == rhs.controllerWriteIoBandwidthKBps)||((this.controllerWriteIoBandwidthKBps!= null)&&this.controllerWriteIoBandwidthKBps.equals(rhs.controllerWriteIoBandwidthKBps))))&&((this.controllerWriteIoPpm == rhs.controllerWriteIoPpm)||((this.controllerWriteIoPpm!= null)&&this.controllerWriteIoPpm.equals(rhs.controllerWriteIoPpm))))&&((this.controllerStorageTierSsdUsageBytes == rhs.controllerStorageTierSsdUsageBytes)||((this.controllerStorageTierSsdUsageBytes!= null)&&this.controllerStorageTierSsdUsageBytes.equals(rhs.controllerStorageTierSsdUsageBytes))))&&((this.hypervisorNumWriteIops == rhs.hypervisorNumWriteIops)||((this.hypervisorNumWriteIops!= null)&&this.hypervisorNumWriteIops.equals(rhs.hypervisorNumWriteIops))))&&((this.hypervisorTotalReadIoSizeKbytes == rhs.hypervisorTotalReadIoSizeKbytes)||((this.hypervisorTotalReadIoSizeKbytes!= null)&&this.hypervisorTotalReadIoSizeKbytes.equals(rhs.hypervisorTotalReadIoSizeKbytes))))&&((this.controllerNumReadIops == rhs.controllerNumReadIops)||((this.controllerNumReadIops!= null)&&this.controllerNumReadIops.equals(rhs.controllerNumReadIops))))&&((this.totalReadIoSizeKbytes == rhs.totalReadIoSizeKbytes)||((this.totalReadIoSizeKbytes!= null)&&this.totalReadIoSizeKbytes.equals(rhs.totalReadIoSizeKbytes))))&&((this.readIoBandwidthKBps == rhs.readIoBandwidthKBps)||((this.readIoBandwidthKBps!= null)&&this.readIoBandwidthKBps.equals(rhs.readIoBandwidthKBps))))&&((this.controllerTotalReadIoSizeKbytes == rhs.controllerTotalReadIoSizeKbytes)||((this.controllerTotalReadIoSizeKbytes!= null)&&this.controllerTotalReadIoSizeKbytes.equals(rhs.controllerTotalReadIoSizeKbytes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.writeIoBandwidthKBps == rhs.writeIoBandwidthKBps)||((this.writeIoBandwidthKBps!= null)&&this.writeIoBandwidthKBps.equals(rhs.writeIoBandwidthKBps))))&&((this.seqIoPpm == rhs.seqIoPpm)||((this.seqIoPpm!= null)&&this.seqIoPpm.equals(rhs.seqIoPpm))))&&((this.hypervisorTotalIoTimeUsecs == rhs.hypervisorTotalIoTimeUsecs)||((this.hypervisorTotalIoTimeUsecs!= null)&&this.hypervisorTotalIoTimeUsecs.equals(rhs.hypervisorTotalIoTimeUsecs))))&&((this.hypervisorNumWriteIo == rhs.hypervisorNumWriteIo)||((this.hypervisorNumWriteIo!= null)&&this.hypervisorNumWriteIo.equals(rhs.hypervisorNumWriteIo))))&&((this.guestMemoryUsageBytes == rhs.guestMemoryUsageBytes)||((this.guestMemoryUsageBytes!= null)&&this.guestMemoryUsageBytes.equals(rhs.guestMemoryUsageBytes))))&&((this.hypervisorNumReadIops == rhs.hypervisorNumReadIops)||((this.hypervisorNumReadIops!= null)&&this.hypervisorNumReadIops.equals(rhs.hypervisorNumReadIops))))&&((this.numSeqIo == rhs.numSeqIo)||((this.numSeqIo!= null)&&this.numSeqIo.equals(rhs.numSeqIo))))&&((this.hypervisorCpuUsagePpm == rhs.hypervisorCpuUsagePpm)||((this.hypervisorCpuUsagePpm!= null)&&this.hypervisorCpuUsagePpm.equals(rhs.hypervisorCpuUsagePpm))))&&((this.hypervisorAvgWriteIoLatencyUsecs == rhs.hypervisorAvgWriteIoLatencyUsecs)||((this.hypervisorAvgWriteIoLatencyUsecs!= null)&&this.hypervisorAvgWriteIoLatencyUsecs.equals(rhs.hypervisorAvgWriteIoLatencyUsecs))))&&((this.hypervisorConsumedMemoryBytes == rhs.hypervisorConsumedMemoryBytes)||((this.hypervisorConsumedMemoryBytes!= null)&&this.hypervisorConsumedMemoryBytes.equals(rhs.hypervisorConsumedMemoryBytes))))&&((this.timespanUsecs == rhs.timespanUsecs)||((this.timespanUsecs!= null)&&this.timespanUsecs.equals(rhs.timespanUsecs))))&&((this.controllerAvgWriteIoLatencyUsecs == rhs.controllerAvgWriteIoLatencyUsecs)||((this.controllerAvgWriteIoLatencyUsecs!= null)&&this.controllerAvgWriteIoLatencyUsecs.equals(rhs.controllerAvgWriteIoLatencyUsecs))))&&((this.hypervisorWriteIoBandwidthKBps == rhs.hypervisorWriteIoBandwidthKBps)||((this.hypervisorWriteIoBandwidthKBps!= null)&&this.hypervisorWriteIoBandwidthKBps.equals(rhs.hypervisorWriteIoBandwidthKBps))))&&((this.controllerNumIops == rhs.controllerNumIops)||((this.controllerNumIops!= null)&&this.controllerNumIops.equals(rhs.controllerNumIops))))&&((this.hypervisorMemoryAssignedBytes == rhs.hypervisorMemoryAssignedBytes)||((this.hypervisorMemoryAssignedBytes!= null)&&this.hypervisorMemoryAssignedBytes.equals(rhs.hypervisorMemoryAssignedBytes))))&&((this.controllerTotalReadIoTimeUsecs == rhs.controllerTotalReadIoTimeUsecs)||((this.controllerTotalReadIoTimeUsecs!= null)&&this.controllerTotalReadIoTimeUsecs.equals(rhs.controllerTotalReadIoTimeUsecs))))&&((this.numRandomIo == rhs.numRandomIo)||((this.numRandomIo!= null)&&this.numRandomIo.equals(rhs.numRandomIo))))&&((this.controllerTotalTransformedUsageBytes == rhs.controllerTotalTransformedUsageBytes)||((this.controllerTotalTransformedUsageBytes!= null)&&this.controllerTotalTransformedUsageBytes.equals(rhs.controllerTotalTransformedUsageBytes))))&&((this.avgIoLatencyUsecs == rhs.avgIoLatencyUsecs)||((this.avgIoLatencyUsecs!= null)&&this.avgIoLatencyUsecs.equals(rhs.avgIoLatencyUsecs))))&&((this.hypervisorNumReceivedBytes == rhs.hypervisorNumReceivedBytes)||((this.hypervisorNumReceivedBytes!= null)&&this.hypervisorNumReceivedBytes.equals(rhs.hypervisorNumReceivedBytes))))&&((this.hypervisorAvgIoLatencyUsecs == rhs.hypervisorAvgIoLatencyUsecs)||((this.hypervisorAvgIoLatencyUsecs!= null)&&this.hypervisorAvgIoLatencyUsecs.equals(rhs.hypervisorAvgIoLatencyUsecs))))&&((this.controllerAvgWriteIoSizeKbytes == rhs.controllerAvgWriteIoSizeKbytes)||((this.controllerAvgWriteIoSizeKbytes!= null)&&this.controllerAvgWriteIoSizeKbytes.equals(rhs.controllerAvgWriteIoSizeKbytes))))&&((this.hypervisorMemoryUsagePpm == rhs.hypervisorMemoryUsagePpm)||((this.hypervisorMemoryUsagePpm!= null)&&this.hypervisorMemoryUsagePpm.equals(rhs.hypervisorMemoryUsagePpm))))&&((this.ioBandwidthKBps == rhs.ioBandwidthKBps)||((this.ioBandwidthKBps!= null)&&this.ioBandwidthKBps.equals(rhs.ioBandwidthKBps))))&&((this.hypervisorNumIo == rhs.hypervisorNumIo)||((this.hypervisorNumIo!= null)&&this.hypervisorNumIo.equals(rhs.hypervisorNumIo))))&&((this.controllerRandomIoPpm == rhs.controllerRandomIoPpm)||((this.controllerRandomIoPpm!= null)&&this.controllerRandomIoPpm.equals(rhs.controllerRandomIoPpm))))&&((this.guestMemorySwappedOutBytes == rhs.guestMemorySwappedOutBytes)||((this.guestMemorySwappedOutBytes!= null)&&this.guestMemorySwappedOutBytes.equals(rhs.guestMemorySwappedOutBytes))))&&((this.controllerAvgReadIoSizeKbytes == rhs.controllerAvgReadIoSizeKbytes)||((this.controllerAvgReadIoSizeKbytes!= null)&&this.controllerAvgReadIoSizeKbytes.equals(rhs.controllerAvgReadIoSizeKbytes))))&&((this.controllerReadIoBandwidthKBps == rhs.controllerReadIoBandwidthKBps)||((this.controllerReadIoBandwidthKBps!= null)&&this.controllerReadIoBandwidthKBps.equals(rhs.controllerReadIoBandwidthKBps))))&&((this.hypervisorNumIops == rhs.hypervisorNumIops)||((this.hypervisorNumIops!= null)&&this.hypervisorNumIops.equals(rhs.hypervisorNumIops))))&&((this.controllerTotalIoSizeKbytes == rhs.controllerTotalIoSizeKbytes)||((this.controllerTotalIoSizeKbytes!= null)&&this.controllerTotalIoSizeKbytes.equals(rhs.controllerTotalIoSizeKbytes))))&&((this.avgReadIoLatencyUsecs == rhs.avgReadIoLatencyUsecs)||((this.avgReadIoLatencyUsecs!= null)&&this.avgReadIoLatencyUsecs.equals(rhs.avgReadIoLatencyUsecs))))&&((this.controllerNumReadIo == rhs.controllerNumReadIo)||((this.controllerNumReadIo!= null)&&this.controllerNumReadIo.equals(rhs.controllerNumReadIo))))&&((this.hypervisorSwapOutRateKBps == rhs.hypervisorSwapOutRateKBps)||((this.hypervisorSwapOutRateKBps!= null)&&this.hypervisorSwapOutRateKBps.equals(rhs.hypervisorSwapOutRateKBps))))&&((this.randomIoPpm == rhs.randomIoPpm)||((this.randomIoPpm!= null)&&this.randomIoPpm.equals(rhs.randomIoPpm))))&&((this.avgWriteIoLatencyUsecs == rhs.avgWriteIoLatencyUsecs)||((this.avgWriteIoLatencyUsecs!= null)&&this.avgWriteIoLatencyUsecs.equals(rhs.avgWriteIoLatencyUsecs))))&&((this.controllerAvgIoLatencyUsecs == rhs.controllerAvgIoLatencyUsecs)||((this.controllerAvgIoLatencyUsecs!= null)&&this.controllerAvgIoLatencyUsecs.equals(rhs.controllerAvgIoLatencyUsecs))))&&((this.totalUntransformedUsageBytes == rhs.totalUntransformedUsageBytes)||((this.totalUntransformedUsageBytes!= null)&&this.totalUntransformedUsageBytes.equals(rhs.totalUntransformedUsageBytes))))&&((this.totalIoTimeUsecs == rhs.totalIoTimeUsecs)||((this.totalIoTimeUsecs!= null)&&this.totalIoTimeUsecs.equals(rhs.totalIoTimeUsecs))))&&((this.numWriteIops == rhs.numWriteIops)||((this.numWriteIops!= null)&&this.numWriteIops.equals(rhs.numWriteIops))))&&((this.numIops == rhs.numIops)||((this.numIops!= null)&&this.numIops.equals(rhs.numIops))))&&((this.hypervisorAvgReadIoLatencyUsecs == rhs.hypervisorAvgReadIoLatencyUsecs)||((this.hypervisorAvgReadIoLatencyUsecs!= null)&&this.hypervisorAvgReadIoLatencyUsecs.equals(rhs.hypervisorAvgReadIoLatencyUsecs))))&&((this.totalTransformedUsageBytes == rhs.totalTransformedUsageBytes)||((this.totalTransformedUsageBytes!= null)&&this.totalTransformedUsageBytes.equals(rhs.totalTransformedUsageBytes))))&&((this.controllerNumWriteIo == rhs.controllerNumWriteIo)||((this.controllerNumWriteIo!= null)&&this.controllerNumWriteIo.equals(rhs.controllerNumWriteIo))))&&((this.hypervisorNumReadIo == rhs.hypervisorNumReadIo)||((this.hypervisorNumReadIo!= null)&&this.hypervisorNumReadIo.equals(rhs.hypervisorNumReadIo))))&&((this.controllerUserBytes == rhs.controllerUserBytes)||((this.controllerUserBytes!= null)&&this.controllerUserBytes.equals(rhs.controllerUserBytes))))&&((this.controllerStorageTierDasSataConfiguredPinnedBytes == rhs.controllerStorageTierDasSataConfiguredPinnedBytes)||((this.controllerStorageTierDasSataConfiguredPinnedBytes!= null)&&this.controllerStorageTierDasSataConfiguredPinnedBytes.equals(rhs.controllerStorageTierDasSataConfiguredPinnedBytes))));
    }

}
