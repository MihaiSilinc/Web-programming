package webubb.repository;


import webubb.model.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Repository {
    private final ArrayList<String> images;
    private final Connection connection;
    private Integer moves;

    public Repository(String username)
    {
        connection = DBManager.connect();
        images = new ArrayList<>(Arrays.asList("1.jpg",
                "2.jpg",
                "3.jpg",
                "4.jpg",
                "5.jpg",
                "6.jpg",
                "7.jpg",
                "8.jpg",
                "imgin.jpg"
                ));
        retriveUser(username);
        moves = retriveMoves(username);
    }

    public Integer getMoves(String username){
        return this.retriveMoves(username);
    }



    private Integer initializeMoves(String username){
        int count = 0;
        try{
            String SqlStatement = "INSERT INTO moves (username,moves) VALUES (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SqlStatement);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,count);
            preparedStatement.execute();
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return 0;
    }


    public void increaseMoves(String username){
        this.moves += 1;
        try{
            String SqlStatement = "UPDATE moves SET moves = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SqlStatement);
            preparedStatement.setInt(1,this.moves);
            preparedStatement.setString(2,username);
            preparedStatement.execute();
        }
        catch (Exception err){
            err.printStackTrace();
        }
    }

    public Integer retriveMoves(String username){
        Integer moves = null;
        try {
            String SqlStatement = "SELECT * FROM moves WHERE username = '" + username + "'";
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SqlStatement);
            if (!resultSet.next()){
                moves = initializeMoves(username);
            }

            moves = resultSet.getInt("moves");

        }
        catch (Exception err){
            err.printStackTrace();
        }
        return moves;
    }

    private Integer restartMoves(String  username){
        try{
            String SqlStatement = "UPDATE moves SET moves = ? where username = ?";
            PreparedStatement statement = connection.prepareStatement(SqlStatement);
            statement.setInt(1,0);
            statement.setString(2,username);
            statement.execute();
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return 0;
    }

    private void insertUser(String username,String password)
    {
        try
        {
            String SqlStatement = "INSERT INTO users (username,password) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(SqlStatement);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.execute();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }

    private void retriveUser(String username){
        try{
            String SqlStatement = "SELECT * FROM game where username= '" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlStatement);

            if (!resultSet.next()){
                initializeTable(username);
                return;
            }
        }
        catch (Exception err){
            err.printStackTrace();
        }
    }

    private  void initializeTable(String username){
        Collections.shuffle(images);
        Integer pos = 1;
        while (pos <= images.size()){
            addImage(username,pos,images.get(pos-1));
            pos += 1;
        }
    }

    private void restartTable(String username){
        Collections.shuffle(images);
        Integer pos = 1;
        while (pos <= images.size())
        {
            updateImage(username,pos,images.get(pos - 1));
            pos += 1;
        }
    }

    public void addImage(String username,Integer pos, String image){
        try {
            String SqlStatement = "INSERT INTO game (username,position,image) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SqlStatement);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,pos);
            preparedStatement.setString(3,image);
            preparedStatement.execute();
        }
        catch (Exception err){
            err.printStackTrace();
        }
    }

    public void updateImage(String username,Integer pos, String image){
        try {
            String SqlStatement = "UPDATE game SET image = ? WHERE position = ? and username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SqlStatement);
            preparedStatement.setString(1,image);
            preparedStatement.setInt(2,pos);
            preparedStatement.setString(3,username);
            preparedStatement.execute();
        }
        catch (Exception err){
            err.printStackTrace();
        }
    }

    public HashMap<Integer,String> findAll(String username){

        HashMap<Integer,String> table = new HashMap<>();
        try{

            String SqlStatement = "SELECT * FROM game WHERE username= '" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlStatement);
            while (resultSet.next()){
                table.put(resultSet.getInt("position"),resultSet.getString("image"));
            }
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
        return table;
    }

    public void initialize(String username){
        restartTable(username);
        this.moves = restartMoves(username);
    }


}
