package common;

import auth.AccountsManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public final class Utils implements Constants{

    public static BufferedImage getImageFromSource(String fileName) {
        InputStream inputStream = Utils.class.getResourceAsStream(IMAGE_DIR + fileName);
        BufferedImage image;
        try {
            assert inputStream != null;
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot access resource!");
        }
        return image;
    }

    public static byte[] charToBytes(char[] charArray) {
        return new String(charArray).codePoints()
                // Collect the code points into a StringBuilder and then a String,
                // and finally get the bytes using UTF-8 encoding.
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .getBytes(StandardCharsets.UTF_8);
    }

    /*
     * Saves object to disk at the specified file
     * @param object
     * @param target
     */
    public  static <Item> void commitToFile(Item item, String target) {
        URL authURL = AccountsManager.class.getResource(target);
        if (authURL == null) {
            logger.log(Level.INFO,
                    "Could not locate " + target + "!");
            throw new RuntimeException("Commit failed!");
        }
        File file = null;
        try {
            file = new File(authURL.toURI());
        } catch (URISyntaxException e1) {
            System.exit(0);
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.close();
        } catch (NotSerializableException e) {
            logger.log(Level.INFO,
                    "Could not serialize object " + item.getClass().getName() +"!");
            throw new RuntimeException(e.getMessage() + item.getClass().getName());
        } catch (IOException e) {
            logger.log(Level.INFO,
                    "Could not write to " + target +"!");
            throw new RuntimeException(e.getMessage());
        }
    }


    public static <Item> Item loadFromFile(String source) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Item object = null;
        URL url;
        try {
            url = AccountsManager.class.getResource(source);
            if (url == null)
                throw new NullPointerException(source + " not found!");
        } catch (NullPointerException e) {
            logger.info("Empty " + source + " file expected.");
            throw new RuntimeException(e.getMessage());
        }
        try {
            fis = new FileInputStream(new File(url.toURI()));
            ois = new ObjectInputStream(fis);
            object = (Item) ois.readObject();
        } catch (URISyntaxException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return object;
    }
}
