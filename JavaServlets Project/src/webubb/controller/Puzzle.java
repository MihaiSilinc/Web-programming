package webubb.controller;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Asset;
import webubb.model.DBManager;
import webubb.domain.ImageTile;
import webubb.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle extends HttpServlet {

    public Puzzle(){
        super();
    }
    private Repository table;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getSession().getAttribute("username").toString();
        //this.username = request.getParameter("username").split("\n")[0];
        this.table = new Repository(username);
        HashMap<Integer, String> data = table.findAll(username);
        System.out.println(data);
        System.out.println(table.getMoves(username).toString());
        data.put(-1, table.getMoves(username).toString());
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = request.getParameter("position");
        String fullImage = request.getParameter("source");
        System.out.println("FULLLL");
        System.out.println(fullImage);
        String image = fullImage;
        System.out.println("IMG");
        System.out.println(image);
        String username = request.getSession().getAttribute("username").toString();
        Integer position = Integer.valueOf(index);
        HashMap<Integer, String> maps  = swap(username,position, image);
        assert maps != null;
        System.out.println(table.getMoves(username).toString());
        try
        {
            maps.put(-1, table.getMoves(username).toString());
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
        if (maps.size()>0)
        {
            String json = new Gson().toJson(maps);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    private HashMap<Integer, String> swap(String username,Integer position, String image){
        if (image.equals("imgin.jpg")) return null;
        Integer replacement = getReplacement(username,position, image);
        if (replacement == -1) return null;
        HashMap<Integer, String> updated = new HashMap<>();
        updated.put(position, "imgin.jpg");
        updated.put(replacement, image);
        table.updateImage(username,position,"imgin.jpg");
        table.updateImage(username,replacement, image);
        table.increaseMoves(username);
        return updated;
    }

    private Integer getReplacement(String username,Integer position, String image) {
        HashMap<Integer, String> data = table.findAll(username);
        String current = null;
        switch (position) {
            case 1: {
                current = data.get(2);
                if (current.equals("imgin.jpg")) return 2;
                current = data.get(4);
                if (current.equals("imgin.jpg")) return 4;
                break;
            }
            case 2: {
                current = data.get(1);
                if (current.equals("imgin.jpg")) return 1;
                current = data.get(5);
                if (current.equals("imgin.jpg")) return 5;
                current = data.get(3);
                if (current.equals("imgin.jpg")) return 3;
                break;
            }
            case 3: {
                current = data.get(2);
                if (current.equals("imgin.jpg")) return 2;
                current = data.get(6);
                if (current.equals("imgin.jpg")) return 6;
                break;
            }
            case 4: {
                current = data.get(1);
                if (current.equals("imgin.jpg")) return 1;
                current = data.get(5);
                if (current.equals("imgin.jpg")) return 5;
                current = data.get(7);
                if (current.equals("imgin.jpg")) return 7;
                break;
            }
            case 5: {
                current = data.get(2);
                if (current.equals("imgin.jpg")) return 2;
                current = data.get(4);
                if (current.equals("imgin.jpg")) return 4;
                current = data.get(6);
                if (current.equals("imgin.jpg")) return 6;
                current = data.get(8);
                if (current.equals("imgin.jpg")) return 8;
                break;
            }
            case 6: {
                current = data.get(3);
                if (current.equals("imgin.jpg")) return 3;
                current = data.get(5);
                if (current.equals("imgin.jpg")) return 5;
                current = data.get(9);
                if (current.equals("imgin.jpg")) return 9;
                break;
            }
            case 7: {
                current = data.get(4);
                if (current.equals("imgin.jpg")) return 4;
                current = data.get(8);
                if (current.equals("imgin.jpg")) return 8;
                break;
            }
            case 8: {
                current = data.get(7);
                if (current.equals("imgin.jpg")) return 7;
                current = data.get(5);
                if (current.equals("imgin.jpg")) return 5;
                current = data.get(9);
                if (current.equals("imgin.jpg")) return 9;
                break;

            }
            case 9:{
                current = data.get(6);
                if (current.equals("imgin.jpg")) return 6;
                current = data.get(8);
                if (current.equals("imgin.jpg")) return 8;
                break;
            }
        }
        return -1;

    }


}
