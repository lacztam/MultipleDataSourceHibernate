package hu.multipledatasource.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/memory")
public class MemoryListener {

    @GetMapping("/status")
    public List<String> getMemoryStatus(){

        double divider = 1024.0d;

        // Get current size of heap in bytes
        double heapSizeInBytes = Runtime.getRuntime().totalMemory(); // bytes
        double heapSizeInKB = Runtime.getRuntime().totalMemory() / divider; // kilobytes
        double heapSizeInMB = Runtime.getRuntime().totalMemory() / divider / divider; // Mb
        double heapSizeInGB = Runtime.getRuntime().totalMemory() / divider / divider / divider; // GB

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        double heapMaxSizeInBytes = Runtime.getRuntime().maxMemory();
        double heapMaxSizeInKiloBytes = Runtime.getRuntime().maxMemory() / divider;
        double heapMaxSizeInMb = Runtime.getRuntime().maxMemory() / divider / divider;
        double heapMaxSizeInGB = Runtime.getRuntime().maxMemory() / divider / divider / divider;

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        double heapFreeSizeInBytes = Runtime.getRuntime().freeMemory();
        double heapFreeSizeInKB = Runtime.getRuntime().freeMemory() / divider;
        double heapFreeSizeInMB = Runtime.getRuntime().freeMemory() / divider / divider;
        double heapFreeSizeInGM = Runtime.getRuntime().freeMemory() / divider / divider / divider;

        String heapSizeBytes = "heapSizeInBytes: " + heapSizeInBytes;
        String heapSizeKB = "heapSizeKB: " + heapSizeInKB;
        String heapSizeMB = "heapSizeMB: " + heapSizeInMB;
        String heapSizeGB = "heapSizeGB: " + heapSizeInGB;

        String heapMaxSizeBytes = "heapMaxSizeBytes: " + heapMaxSizeInBytes;
        String heapMaxSizeKB = "heapMaxSizeKB: " + heapMaxSizeInKiloBytes;
        String heapMaxSizeMB = "heapMaxSizeMB: " + heapMaxSizeInMb;
        String heapMaxSizGB = "heapMaxSizeGB: " + heapMaxSizeInGB;

        String heapFreeSizeBytes = "heapFreeSizeBytes: " + heapFreeSizeInBytes;
        String heapFreeSizeKB = "heapFreeSizeKB: " + heapFreeSizeInKB;
        String heapFreeSizeMB = "heapFreeSizeMB: " + heapFreeSizeInMB;
        String heapFreeSizeGB = "heapFreeSizeGB: " + heapFreeSizeInGM;

        List<String> heapList = new ArrayList<>();
        heapList.add(heapSizeBytes);
        heapList.add(heapSizeKB);
        heapList.add(heapSizeMB);
        heapList.add(heapSizeGB);
        heapList.add("");

        heapList.add(heapMaxSizeBytes);
        heapList.add(heapMaxSizeKB);
        heapList.add(heapMaxSizeMB);
        heapList.add(heapMaxSizGB);
        heapList.add("");

        heapList.add(heapFreeSizeBytes);
        heapList.add(heapFreeSizeKB);
        heapList.add(heapFreeSizeMB);
        heapList.add(heapFreeSizeGB);
        heapList.add("");

        return heapList;
    }

}
