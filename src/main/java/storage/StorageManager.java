package storage;

public class StorageManager {

    private static ImportAndExportStorage base;

    public static void setImportAdnExport(ImportAndExportStorage theStorage) {
        base = theStorage;
    }

    public static ImportAndExportStorage getStorage(String fileName) {
        base.setBaseName(fileName);
        return base;
    }

    public static ImportAndExportStorage getBase() {
        return base;
    }
}
