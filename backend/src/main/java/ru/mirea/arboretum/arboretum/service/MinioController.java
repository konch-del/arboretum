package ru.mirea.arboretum.arboretum.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MinioController {

   private final static MinioClient minioClient =
           MinioClient.builder()
                   .endpoint("http://minio:9000")
                   .credentials("minioadmin", "minioadmin")
                   .build();

   public static void createBucket(Long plantId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       minioClient.makeBucket(
               MakeBucketArgs
                       .builder()
                       .bucket(String.valueOf(plantId))
                       .build());
   }

   public static void loadImage(Long plantId, File image) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       minioClient.putObject(PutObjectArgs
               .builder()
               .bucket(String.valueOf(plantId))
               .object(image.getName())
               .stream(new FileInputStream(image), image.length(), image.getPath().length())
               .build());
   }

   public static GetObjectResponse getImage(Long plantId, String image){
       try (GetObjectResponse stream =
                    minioClient.getObject(GetObjectArgs
                            .builder()
                            .bucket(String.valueOf(plantId))
                            .object(image)
                            .build())) {
           return stream;
       } catch (ServerException e) {
           throw new RuntimeException(e);
       } catch (InsufficientDataException e) {
           throw new RuntimeException(e);
       } catch (ErrorResponseException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       } catch (InvalidKeyException e) {
           throw new RuntimeException(e);
       } catch (InvalidResponseException e) {
           throw new RuntimeException(e);
       } catch (XmlParserException e) {
           throw new RuntimeException(e);
       } catch (InternalException e) {
           throw new RuntimeException(e);
       }
   }

   @SneakyThrows
   public static List<File> getImages(Long plantId){
       Iterable<Result<Item>> results = minioClient.listObjects(
               ListObjectsArgs.builder().bucket(String.valueOf(plantId)).build());
       Iterator iterator = results.iterator();
       List<File> images = new ArrayList<>();
       while (iterator.hasNext()){
            Item result = ((Result<Item>) iterator.next()).get();
            images.add(copyInputStreamToFile(
                    getImage(plantId, result.objectName()),
                    result.objectName()
            ));
       }
       return images;
   }

    private static File copyInputStreamToFile(GetObjectResponse inputStream, String name) throws IOException {
        File file = new File("src/main/resources/" + name);
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[5242880];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
        return file;
    }
}


