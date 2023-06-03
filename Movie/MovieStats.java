import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MovieStats extends JFrame implements ActionListener{
    private static ArrayList<Movie> moviesList;
    private JButton exit;
    private static int  mo,numCateg;
    private static String categ;
    private static Movie oldestMovie,newestMovie;

     MovieStats(ArrayList<Movie> movies){

        MovieStats.moviesList = movies;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Statistics");
        this.setBounds(370,205,750,370);
        this.setLayout(null);

        JLabel title = new JLabel("Statistics");
        title.setFont(new Font("Carlson",Font.PLAIN,20));
        title.setBounds(350,-70,190,190);
        title.setForeground(Color.WHITE);

        exit = new JButton("exit");
        exit.setBounds(330,285,120,40);
        exit.setFont( new Font("Helvetica",Font.PLAIN,17));
        exit.addActionListener(this);

        calculateStats();

        MovieField numMovies = new MovieField("Total Number :",130,10,250,50,200,100);
        MovieField averageDuration = new MovieField("Average Duration :",100,50,250,90,200,100);
        MovieField biggestCategory = new MovieField("Biggest Category :",100,90,250,130,200,100);
        MovieField CategoryNum = new MovieField("Number in Categoty :",80,130,250,170,200,100);
        MovieField oldMovie = new MovieField("Oldest Movie :",130, 120,250,210,200,200);
        MovieField newMovie = new MovieField("Newest Movie :",120,160,250,250,200,200);

        numMovies.getField().setText(String.valueOf(moviesList.size())); numMovies.getField().setEditable(false);
        averageDuration.getField().setText(String.valueOf(mo)); averageDuration.getField().setEditable(false);
        biggestCategory.getField().setText(categ); biggestCategory.getField().setEditable(false);
        CategoryNum.getField().setText(String.valueOf(numCateg)); biggestCategory.getField().setEditable(false);
        oldMovie.getField().setText(oldestMovie.getTitle()); oldMovie.getField().setEditable(false);
        newMovie.getField().setText(newestMovie.getTitle()); newMovie.getField().setEditable(false);
        
        this.add(title);
        this.add(numMovies.getLabel()); this.add(numMovies.getField());
        this.add(averageDuration.getLabel()); this.add(averageDuration.getField());
        this.add(biggestCategory.getLabel()); this.add(biggestCategory.getField());
        this.add(CategoryNum.getLabel()); this.add(CategoryNum.getField());
        this.add(oldMovie.getLabel()); this.add(oldMovie.getField());
        this.add(newMovie.getLabel()); this.add(newMovie.getField());
        this.add(exit);
        this.getContentPane().setBackground(new Color(0x575658));
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void calculateStats(){
        int sum = 0;
        String[] options = {"Drama", "Romance", "Action", "Thriller", "Comedy","Other"};
        int[] arr = new int[options.length];
        for (Movie movie : moviesList) {
            sum += movie.getDuration();
            for (int i=0 ; i<arr.length; i++){
                if(movie.getCategory().equals(options[i])){
                    arr[i]++;
                }
            }
        }
        mo = sum/moviesList.size();
        int max = arr[0];
        int j = 0;
        for (int i=1 ; i<arr.length; i++){
           if(arr[i] > max){
                j = i;
           }
        }
        categ = options[j];
        numCateg = arr[j];      
        
        oldestMovie = moviesList.get(0); 
        for (Movie movie : moviesList) {
            if (movie.getReleaseDate().compareTo(oldestMovie.getReleaseDate()) < 0) {
                oldestMovie = movie; 
            }
        }

        newestMovie = moviesList.get(0); 
        for (Movie movie : moviesList) {
            if (movie.getReleaseDate().compareTo(newestMovie.getReleaseDate()) > 0) {
                newestMovie = movie; 
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            this.dispose();
        }
    }
}
