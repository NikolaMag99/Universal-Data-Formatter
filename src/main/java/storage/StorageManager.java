package storage;

public class StorageManager {

    private static ImportAndExportStorage base;

    public static void setImportAdnExport(ImportAndExportStorage theStorage) {
        base = theStorage;
    }

    public static void setBase(ImportAndExportStorage base) {
        StorageManager.base = base;
    }

    public static ImportAndExportStorage getStorage(String fileName) {
        base.setBaseName(fileName);
        return base;
    }

    public static ImportAndExportStorage getBase() {
        return base;
    }
}
