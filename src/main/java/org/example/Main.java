package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Main {
    public static final String FILE = "src/main/java/org/example/file";
    public static final String FILE_JSON = FILE + ".json";
    public static final String FILE_BIN = FILE + ".bin";
    public static final String FILE_XML = FILE + ".xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void main(String[] args) throws JsonProcessingException {

        Student student = new Student("Сергей", 35, 5);

        System.out.println("До сериализации: " + student);
        System.out.println(student);

        serializeStudent(FILE_JSON, student);
        serializeStudent(FILE_XML, student);
        serializeStudent(FILE_BIN, student);

        System.out.println("______________");

        System.out.println("После сериализации:");
        System.out.println("json:\n" + deserializeStudent(FILE_JSON));
        System.out.println("bin:\n" + deserializeStudent(FILE_BIN));
        System.out.println("xml:\n" + deserializeStudent(FILE_XML));
    }

    /**
     * Сериализует объект Student в разные форматы
     * @param fileName
     * @param student
     */
    public static void serializeStudent(String fileName, Student student) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT,
                        true);
                objectMapper.writeValue(new File(fileName), student);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(student);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализует объект
     * @param fileName путь к файлу
     * @return возвращем экземпляр студента
     */
    public static Student deserializeStudent(String fileName) {
        Student student = new Student();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectMapper.readValue(file, Student.class);
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        student = (Student) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    student = xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return student;
    }
}

