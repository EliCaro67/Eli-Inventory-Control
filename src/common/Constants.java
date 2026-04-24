package common;

import java.util.logging.Logger;

public interface Constants {
    Logger logger = Logger.getLogger("INV_CTL");
    int FRAME_WIDTH = 800, FRAME_HEIGHT = 600;
    String IMAGE_DIR = "/res/images/";
    String KEY_DB = "/auth/key.db";
    String ADMIN_DB = "/auth/admin.db";
    String PERSONNEL_DB = "/dbase/personnel.db";
    String INVENTORY_DB = "/dbase/inventory.db";

}
