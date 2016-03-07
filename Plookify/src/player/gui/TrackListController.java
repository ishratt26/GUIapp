/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import common.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import player.logic.Track;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class TrackListController implements Initializable {

    @FXML
    private TableView<Track> tableView;
    @FXML
    private TableColumn<Track, String> nameColumn;
    @FXML
    private TableColumn<Track, String> artistColumn;
    @FXML
    private TableColumn<Track, String> genreColumn;
    @FXML
    private TableColumn<Track, Integer> lengthColumn;
    /**
     * Initializes the controller class.
     */
    public ArrayList<Track> allTracks;
    public static ArrayList<String> trackNames;
    public static ArrayList<String> trackArtist;
    public static ArrayList<String> trackGenre;
    public static ArrayList<String> trackPaths;
    public static ArrayList<String> trackLength;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            allTracks = new ArrayList<Track>();
            trackNames = new ArrayList<String>();
            trackArtist = new ArrayList<String>();
            trackGenre = new ArrayList<String>();
            trackLength = new ArrayList<String>();
            trackPaths = new ArrayList<String>();

            setTrackInfo();
            setTracks();
            showtracks();
        } catch (IOException ex) {
            Logger.getLogger(TrackListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showtracks() throws IOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("trackArtist"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("trackLength"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("trackGenre"));

        tableView.setItems(getTrack());

    }

    public ObservableList<Track> getTrack() {
        ObservableList<Track> tracks = FXCollections.observableArrayList();
        for (int i = 0; i < allTracks.size(); i++) {
            tracks.add(allTracks.get(i));
        }
        return tracks;
    }

    public void setTracks() {
        for (int i = 0; i < trackNames.size(); i++) {
            String name = trackNames.get(i);
            String artist = trackArtist.get(i);
            String genre = trackGenre.get(i);
            String path = trackPaths.get(i);
            String length = trackLength.get(i);
            allTracks.add(new Track(name, artist, genre, length, path));
        }

    }

    public void setTrackInfo() {

        Database db = new Database();
        Connection c = null;
        Statement statement;
        try {
            statement = db.getConnection().createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT trackName, trackLength, trackPath from track");
            while (rs.next()) {
                trackNames.add(rs.getString("trackName"));
                trackLength.add(rs.getString("trackLength"));
                trackPaths.add(rs.getString("trackPath"));
            }

            rs = statement.executeQuery("SELECT artistName from artist,track where trackArtist = artistID");
            while (rs.next()) {
                trackArtist.add(rs.getString("artistName"));
            }

            rs = statement.executeQuery("SELECT genreName from genre,track where trackGenre = genreID");
            while (rs.next()) {
                trackGenre.add(rs.getString("genreName"));
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }

}
