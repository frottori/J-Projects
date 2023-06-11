import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MovieRegister extends JFrame implements ActionListener{

    JMenuItem stats,about,exit;
    JButton save;
    MovieField title, releaseDate, duration, category, director, language, rating; 
    static ArrayList<Movie> moviesList;
    String[] optionsCat;

    MovieRegister() {
        moviesList = new ArrayList<>();
        MovieRead();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Register Movie");
        this.setBounds(370,150,750,450);
        this.setLayout(null);

        JMenuBar bar = new JMenuBar();

        stats = new JMenuItem("Statistics");
        stats.addActionListener(this);
        about = new JMenuItem("About");
        about.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        JLabel windowTitle = new JLabel("Movie");
        windowTitle.setFont(new Font("Carlson",Font.PLAIN,20));
        windowTitle.setBounds(350,-75,190,190);
        windowTitle.setForeground(Color.WHITE);

        title = new MovieField("Title :",200,10,250,50,100,100);
        releaseDate = new MovieField("Release Date :",130,50,250,90,200,100);
        duration = new MovieField("Duration :",167,90,250,130,200,100);

        String[] optionsCat = {"Drama", "Romance", "Action", "Thriller", "Comedy","Other"};
        category = new MovieField("Category :", 163, 130,250,170,200,100,optionsCat);
        director = new MovieField("Director :",170,170,250,210,200,100);
        
        String[] optionsLan = {"English","Spanish","Russian","French","Greek","Other"};
        language = new MovieField("Language :",158,210,250,250,200,100,optionsLan);
        rating = new MovieField("Rating :",182,250,250,290,200,100);

        save = new JButton("Save");
        save.setBounds(330,330,120,40);
        save.setFont( new Font("Helvetica",Font.PLAIN,17));
        save.addActionListener(this);
        
        bar.add(stats);
        bar.add(about);
        bar.add(exit);
        bar.setLayout(new FlowLayout());

        this.setJMenuBar(bar);
        this.add(windowTitle);
        this.add(title.getLabel()); this.add(title.getField());
        this.add(releaseDate.getLabel()); this.add(releaseDate.getField());
        this.add(duration.getLabel()); this.add(duration.getField());
        this.add(category.getLabel()); this.add(category.getBox());
        this.add(director.getLabel()); this.add(director.getField());
        this.add(language.getLabel()); this.add(language.getBox());
        this.add(rating.getLabel()); this.add(rating.getField());
        this.add(save);
        this.getContentPane().setBackground(new Color(0x575658));
        this.setResizable(false);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        String code = generateCode();
        Movie newMovie = new Movie();
        if(e.getSource() == save){

            for (Movie movie : moviesList) {
                do { 
                    code =  generateCode();
                }
                while(code == movie.getCode());
                if(movie.getTitle() == title.getField().getText() || movie.getReleaseDate() == releaseDate.getField().getText() ){
                    title.getField().setText("");
                    releaseDate.getField().setText("");
                    duration.getField().setText("");
                    director.getField().setText("");
                    rating.getField().setText("");
                    return;
                }
            }
            if (!title.getField().getText().isEmpty() && !releaseDate.getField().getText().isEmpty() &&
                !duration.getField().getText().isEmpty() && !director.getField().getText().isEmpty() && 
                !rating.getField().getText().isEmpty() ) {
                    newMovie.setCode(code); 
                    newMovie.setTitle(title.getField().getText()); 
                    newMovie.setDuration(Integer.parseInt(duration.getField().getText()));
                    newMovie.setReleaseDate(releaseDate.getField().getText());
                    newMovie.setCategory((String) category.getBox().getSelectedItem()); 
                    newMovie.setDirector(director.getField().getText()); 
                    newMovie.setLanguage((String) language.getBox().getSelectedItem()); 
                    newMovie.setRating(Double.parseDouble(rating.getField().getText()));
                    moviesList.add(newMovie);
                    MovieWrite(newMovie);
            }
            
            title.getField().setText("");
            releaseDate.getField().setText("");
            duration.getField().setText("");
            director.getField().setText("");
            rating.getField().setText("");
        }
        if(e.getSource()==stats){
            new MovieStats(moviesList);
        }
        if(e.getSource()==about){
            new MovieAbout();
        }
        if(e.getSource()==exit){
            int i = JOptionPane.showConfirmDialog(null, "Have you saved?");
            if (i == JOptionPane.YES_OPTION)
                    System.exit(0);
        }    
    }

    private static String generateCode() {
        Random random = new Random();
        int length = 6;
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i=0; i<length; i++) {
            int randomNumber = random.nextInt(10); 
            stringBuilder.append(randomNumber);
        }
        return stringBuilder.toString();
    }

    private static void MovieWrite(Movie movie) {
        String fileName = "movies.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            writer.write(movie.getCode() + "-");
            writer.write(movie.getTitle() + "-");
            writer.write(movie.getReleaseDate() + "-");
            writer.write(movie.getDuration() + "-");
            writer.write(movie.getCategory() + "-");
            writer.write(movie.getDirector() + "-");
            writer.write(movie.getLanguage() + "-");
            writer.write(String.valueOf(movie.getRating()));
            writer.newLine(); 
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void MovieRead() {
        String fileName = "movies.txt";
    
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while (br.ready()) {
                line = br.readLine();
                if ( line == null) {
                    return; 
                }
                String[] words = line.split("-");
                Movie movie = new Movie();
                movie.setCode(words[0]);
                movie.setTitle(words[1]);
                movie.setReleaseDate(words[2]);
                movie.setDuration(Integer.parseInt(words[3]));
                movie.setCategory(words[4]);
                movie.setDirector(words[5]);
                movie.setLanguage(words[6]);
                movie.setRating(Double.parseDouble(words[7]));
                moviesList.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main (String[] args){
        new MovieRegister();
    }
}