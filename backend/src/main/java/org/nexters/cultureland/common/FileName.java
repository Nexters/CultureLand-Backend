package org.nexters.cultureland.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileName {
    private final String fileName;

    private final String extension;

    public FileName(final String fileName, final String extension) {
        this.fileName = fileName;
        this.extension = extension;
    }

    public String createFileNameForSave() {
        return String.format("%s_%s.%s", fileName, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS")), extension);
    }
}
