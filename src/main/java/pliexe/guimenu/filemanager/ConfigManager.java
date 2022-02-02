package pliexe.guimenu.filemanager;

import pliexe.guimenu.GuiMenu;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
//    public static Config getConfig() {
//
//    }

    private static InputStreamReader getConfigContent(File file) throws IOException {
        if(file == null) return null;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder gottenString = new StringBuilder();

        String currentLine;

        int commentCounter = 0;
        int spaceCounter = 0;

        boolean previousConfigEnds = true;

        while((currentLine = reader.readLine()) != null)
        {
            if(currentLine.isEmpty()) {
                gottenString.append(GuiMenu.pluginName + "_SPACE_").append(spaceCounter).append(": ''").append("\n");
                spaceCounter++;
            } else if(previousConfigEnds && currentLine.startsWith("#")) {
                gottenString.append(currentLine.replace("\"", "\\\"").replaceFirst("#", GuiMenu.pluginName + "_COMMENT_" + commentCounter + ": \"")).append("\"\n");
                commentCounter++;
            } else {
                gottenString.append(currentLine).append("\n");
                previousConfigEnds = !currentLine.endsWith(":") && !currentLine.trim().startsWith("-");
            }
        }

        String configStr = gottenString.toString();
        ByteArrayInputStream configStream = new ByteArrayInputStream(configStr.getBytes(StandardCharsets.UTF_8));
        InputStreamReader configStreamReader = new InputStreamReader(configStream);
        reader.close();
        return configStreamReader;
    }
}
