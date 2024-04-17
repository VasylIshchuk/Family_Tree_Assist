import java.io.*;

public class PlantUMLRunner {
    public static  String pathJarUml;

    public static void setPathJarUml(String pathJarUml) {
        PlantUMLRunner.pathJarUml = pathJarUml;
    }

    public static void generateDiagram(String data, String path, String fileName)  {
        File catalog = new File(path);
        catalog.mkdirs();
        String filepath = catalog.getPath() +'/'+ fileName;

        try (FileWriter fileWriter = new FileWriter(filepath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
             bufferedWriter.write(data);
             bufferedWriter.close();

             ProcessBuilder processBuilder =
                     new ProcessBuilder("java", "-jar",
                             pathJarUml,filepath);
             //processBuilder.command("java", "-jar", pathJarUml, filepath);
             Process process = processBuilder.start();
             process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
