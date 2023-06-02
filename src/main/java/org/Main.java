package org;

import Service.ProgrammersService;
import Service.VideosService;
import models.Programmer;
import models.Video;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        ProgrammersService programmersService = new ProgrammersService();
//        ArrayList<Programmer> programmersList = programmersService.getProgrammersList();
//        System.out.println("Programmers list: ");
//        for (Programmer programmer: programmersList) {
//            System.out.println("Name: "+programmer.getName() + " Email: "+programmer.getEmail());
//        }
//
//        Programmer programmer1 = new Programmer("Antonio", "antonio@gmail.com");
//        programmersService.insertProgrammer(programmer1);

        VideosService videosService = new VideosService();
        ArrayList<Video> videosList = videosService.getVideosList();
        System.out.println("Videos list: ");
        for (Video video: videosList) {
            System.out.println("ArtifactLocation: "+video.getArtifactLocation() + " ArtifactFormat: "+video.getArtifactFormat() + " IsMadeBy: "+video.getIsMadeBy() + " HasUsedIn: "+video.getHasUsedIn() + " HasTaggedBy: "+video.getHasTaggedBy() + " IsUsedBy: "+video.getIsUsedBy());
        }

        Video video1 = new Video();
        video1.setArtifactName("videoJena03");
        video1.setArtifactLocation("C:\\Users\\jose\\Desktop\\video1.mp4");
        video1.setArtifactFormat("mp4");
        video1.setIsMadeBy("Antonio");
        video1.setHasUsedIn("Project_One");
        video1.setHasTaggedBy("");
        video1.setIsUsedBy("Antonio");
        videosService.insertVideo(video1);

    }
}