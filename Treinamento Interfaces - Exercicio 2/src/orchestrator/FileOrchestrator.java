package orchestrator;

import interfaces.FileDatabase;
import interfaces.ImageFileDatabase;
import model.MFile;
import model.MFileAnnotationTypeEnum;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileOrchestrator extends FolderOrchestrator implements ImageFileDatabase, FileDatabase {
    File file;

    @Override
    public void saveImageFile(String directory, String content, String nameFile) {
        BufferedImage image;
        try {
            URL url = new URL(content);
            image = ImageIO.read(url);
            ImageIO.write(image, "jpeg", new File(directory + nameFile));
        } catch (IOException e) {
            e.printStackTrace();


        }

    }

    public void saveAllListOfImagesFiles(List<MFile> mImageFileList) {
        for (MFile lista : mImageFileList) {
            System.out.println("DIRECTORY PATH");
            String diretorio = tc.nextLine();
            diretorio += "/image/";

            file = new File(diretorio);
            System.out.println(diretorio);

            if (file.exists()) {

                saveImageFile(diretorio, lista.getContent(), lista.getNameFile());
            } else System.out.println("DIRECTORY NOT FOUND");

        }
    }

    public void saveAllListOfFiles(List<MFile> mFileList) {
        for (MFile lista : mFileList) {
            System.out.println("DIRECTORY PATH");
            String diretorio = tc.nextLine();
            file = new File(diretorio);
            if (file.exists()) {
                saveFile(diretorio, lista.getContent(), lista.getType(), lista.getNameFile());
            } else System.out.println("DIRECTORY NOT FOUND");

        }

    }

    @Override
    public void saveFile(String directory, String content, MFileAnnotationTypeEnum type, String nameFile) {
        String subdirectory;
        if (type.equals(MFileAnnotationTypeEnum.REMINDER)){
            System.out.println("entrei no reminder");
            directory += "/reminder";
        }
        else if (type.equals(MFileAnnotationTypeEnum.IMPORTANT)) {
            directory +="/important";

        } else if(type.equals((MFileAnnotationTypeEnum.SIMPLE))){
            directory = directory;
        } else{
            System.out.println("TYPE NOT EXIST");
        }

        file = new File(directory);
        if (file.exists()) {
            directory = file + "/"+nameFile;

            try {
                BufferedWriter escreverTxt = new BufferedWriter(new FileWriter(directory));
                escreverTxt.write(content);
                escreverTxt.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("DIRECTORY NOT FOUND");


    }

    @Override
    public void listAllImageFiles(String directory) {
        file = new File(directory + "/image");
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            Arrays.sort(afile);
            File files = afile[i];
            System.out.println(files.getName());


        }
    }

    @Override
    public void removeImageFile(String directory, String nameFile) {
        file = new File(directory + nameFile);

        if (file.isFile()) {
            file.delete();
        } else System.out.println("NOT FOUND IMAGE");

//        File [] aFile = file.listFiles();
//        for (File listFile : aFile) {
//            if (listFile.getName().equals(nameFile)) {
//                listFile.delete();
//                System.out.println("Deletada com sucesso");
//            }
//
//
//        }
    }

    @Override
    public void recoveryImageFile(String directory) {


    }

    @Override
    public void recoveryFile(String directory, String nameFile) {


    }

    @Override
    public void removeFile(String directory, String nameFile) {
        file = new File(directory + nameFile);
        if(file.exists()) {
            file.delete();
        } else System.out.println("NOT FOUND");


    }

    @Override
    public void listAllFiles(String directory) {

    }
}
