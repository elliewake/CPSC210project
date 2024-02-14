package ui;

import model.Album;
import model.Artist;
import model.Playlist;
import model.Song;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Playlist playlist;
    private static Scanner scanner;
    private static Artist beatles;
    private static ArrayList<Album> beatlesAlbums;
    private static Artist rollingStones;
    private static ArrayList<Album> stonesAlbums;
    private static Album abbeyRoad;
    private static ArrayList<Song> abbeyRoadSongs;
    private static Album revolver;
    private static ArrayList<Song> revolverSongs;
    private static Album stickyFingers;
    private static ArrayList<Song> stickyFingersSongs;
    private static Album exileOnMainStreet;
    private static ArrayList<Song> exileSongs;
    private static Song comeTogether;
    private static Song something;
    private static Song eleanorRigby;
    private static Song forNoOne;
    private static Song brownSugar;
    private static Song wildHorses;
    private static Song rocksOff;
    private static Song sweetVirginia;

    public static void main(String[] args) {
        playlist = new Playlist();
        scanner = new Scanner(System.in);
        // Abbey Road
        abbeyRoadSongs = new ArrayList<>();
        abbeyRoadSongs.add(comeTogether);
        abbeyRoadSongs.add(something);
        abbeyRoad = new Album(abbeyRoadSongs, 1969, 17, 47);
        // Revolver
        revolverSongs = new ArrayList<>();
        revolverSongs.add(eleanorRigby);
        revolverSongs.add(forNoOne);
        revolver = new Album(revolverSongs, 1966, 14, 35);
        // The Beatles
        beatlesAlbums = new ArrayList<>();
        beatlesAlbums.add(abbeyRoad);
        beatlesAlbums.add(revolver);
        beatles = new Artist("The Beatles", beatlesAlbums, "rock");
        // Sticky Fingers
        stickyFingersSongs = new ArrayList<>();
        stickyFingersSongs.add(brownSugar);
        stickyFingersSongs.add(wildHorses);
        stickyFingers = new Album(stickyFingersSongs, 1971, 10, 46);
        // Exile on Main Street
        exileSongs = new ArrayList<>();
        exileSongs.add(rocksOff);
        exileSongs.add(sweetVirginia);
        exileOnMainStreet = new Album(exileSongs, 1972, 18, 67);
        // The Rolling Stones
        stonesAlbums = new ArrayList<>();
        stonesAlbums.add(stickyFingers);
        stonesAlbums.add(exileOnMainStreet);
        rollingStones = new Artist("The Rolling Stones", stonesAlbums, "rock");
        // Songs
        comeTogether = new Song("Come Together", 4, beatles, abbeyRoad, "rock");
        something = new Song("Something", 3, beatles, abbeyRoad, "rock");
        eleanorRigby = new Song("Eleanor Rigby", 2, beatles, revolver, "rock");
        forNoOne = new Song("For No One", 2, beatles, revolver, "rock");
        brownSugar = new Song("Brown Sugar", 4, rollingStones, stickyFingers, "rock");
        wildHorses = new Song("Wild Horses", 6, rollingStones, stickyFingers, "rock");
        rocksOff = new Song("Rocks Off", 5, rollingStones, exileOnMainStreet, "rock");
        sweetVirginia = new Song("Sweet Virginia", 4, rollingStones, exileOnMainStreet, "rock");

        System.out.print("Welcome. Please type + to add a song, or q to quit\n");

        while (true) {
            // read user input
            String userInput = scanner.nextLine();
            processKey(userInput);

            if (userInput.equals("q")) {
                break;
            }
        }
        scanner.close();
    }

    public static void processKey(String input) {
        if (input.equals("+")) {
            getNewSong();
        } else if (input.equals("q")) {
            System.out.println("Quitting playlist app. Goodbye!");
        } else {
            System.out.println("Please press + to add a song or q to quit");
        }
    }

    public static void getNewSong() {
        System.out.println("Please choose which artist you would like to add a song from"
                + " by typing the corresponding number: \n1. The Beatles \n2. The Rolling Stones");
        String artistChoice = scanner.nextLine();

        if (artistChoice.equals("1")) {
            System.out.println("Please choose which album you'd like to add a song from"
                    + " by typing the corresponding number: \n1. Abbey Road \n2. Revolver");
            getBeatlesSong();
        } else if (artistChoice.equals("2")) {
            System.out.println("Please choose which album you'd like to add a song from"
                    + " by typing the corresponding number: \n1. Sticky Fingers \n2. Exile on Main Street");
            getStonesSong();
        } else {
            System.out.println("Invalid album choice.");
        }
    }

    public static void getBeatlesSong() {
        String albumChoice = scanner.nextLine();

        if (albumChoice.equals("1")) {
            System.out.println("Please choose which song you'd like to add by typing the corresponding number:"
                    + "\n1. Come Together \n2. Something");
            String songChoice = scanner.nextLine();

            if (songChoice.equals("1")) {
                playlist.addSong(comeTogether);
                System.out.println("Added Come Together to playlist!");
                songAdded();
            } else if (songChoice.equals("2")) {
                playlist.addSong(something);
                System.out.println("Added Something to playlist!");
                songAdded();
            } else {
                System.out.println("Invalid song choice.");
            }
        } else if (albumChoice.equals("2")) {
            System.out.println("Please choose which song you'd like to add by typing the corresponding number:"
                    + "\n1. Eleanor Rigby \n2. For No One");
            String songChoice = scanner.nextLine();

            if (songChoice.equals("1")) {
                playlist.addSong(eleanorRigby);
                System.out.println("Added Eleanor Rigby to playlist!");
                songAdded();
            } else if (songChoice.equals("2")) {
                playlist.addSong(forNoOne);
                System.out.println("Added For No One to playlist!");
                songAdded();
            } else {
                System.out.println("Invalid song choice.");
            }
        }
    }

    public static void getStonesSong() {
        String albumChoice = scanner.nextLine();

        if (albumChoice.equals("1")) {
            System.out.println("Please choose which song you'd like to add by typing the corresponding number:"
                    + "\n1. Brown Sugar \n2. Wild Horses");
            String songChoice = scanner.nextLine();

            if (songChoice.equals("1")) {
                playlist.addSong(brownSugar);
                System.out.println("Added Brown Sugar to playlist!");
                songAdded();
            } else if (songChoice.equals("2")) {
                playlist.addSong(wildHorses);
                System.out.println("Added Wild Horses to playlist!");
                songAdded();
            } else {
                System.out.println("Invalid song choice.");
            }
        } else if (albumChoice.equals("2")) {
            System.out.println("Please choose which song you'd like to add by typing the corresponding number:"
                    + "\n1. Rocks Off \n2. Sweet Virginia");
            String songChoice = scanner.nextLine();

            if (songChoice.equals("1")) {
                playlist.addSong(rocksOff);
                System.out.println("Added Rocks Off to playlist!");
                songAdded();
            } else if (songChoice.equals("2")) {
                playlist.addSong(sweetVirginia);
                System.out.println("Added Sweet Virginia to playlist!");
                songAdded();
            } else {
                System.out.println("Invalid song choice.");
            }
        } else {
            System.out.println("Invalid album choice.");
        }

    }

    public static void songAdded() {
        while (true) {
            System.out.println("Press q to quit, \n+ to add another song, or \nv to view current playlist");
            String userInput = scanner.nextLine();

            if (userInput.equals("q")) {
                System.out.println("Quitting playlist app. Goodbye!");
                break;
            } else if (userInput.equals("+")) {
                getNewSong();
            } else if (userInput.equals("v")) {
                viewPlaylist();
            } else {
                System.out.println("Invalid input. Please press q to quit or + to add another song.");
            }
        }
    }

    public static void viewPlaylist() {
        System.out.println("Current Playlist:");
        ArrayList<Song> songs = playlist.getPlaylist();

        for (Song song : songs) {
            System.out.println(song.getTitle() + " by " + song.getArtist().getName());
        }

        System.out.println("Your playlist is " + playlist.getLengthMins() + " minutes long.");
    }
}

