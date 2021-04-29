package phonebook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Phonebook {




    public void exportPhonebook(Map<String, String> contacts, String output) {
        if (contacts == null || output == null ) {
            throw new IllegalArgumentException("Invalid data !");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(output))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
               writer.write( "\n");

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }



    }


}
