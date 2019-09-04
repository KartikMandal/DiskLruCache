package com.kartik.hacker;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;

import com.google.gson.*;

/**
 * 
 * 
 * @author kmandal
 * Write a query to print the respective Department Name and total of employees per department for all departments in Department table
Include departments with 0 employees.
Order the results per total of employees, if 2 or more departments have the same amount of employees then order alphabetically by department name


 * 
 * SELECT DEPARTMENT.NAME, COUNT(EMPLOYEE.ID) AS COUNT_OF_EMPLOYEES_IN_THE_DEPARTMENT
    FROM DEPARTMENT 
    LEFT JOIN EMPLOYEE ON DEPARTMENT.ID = EMPLOYEE.DEPT_ID
    GROUP BY DEPARTMENT.NAME
    ORDER BY COUNT_OF_EMPLOYEES_IN_THE_DEPARTMENT DESC, DEPARTMENT.NAME ASC
 *
 */

public class Solution {
    /*
     * Complete the function below.
     * Base url: https://jsonmock.hackerrank.com/api/movies/search/?Title=
     */
    static String[] getMovieTitles(String substr) {
    	String response;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;
        List<String> titles = new ArrayList<>();
        while (startPage <= totalPages) {
            try {
                URL obj = new URL(
                        "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + startPage);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((response = in.readLine()) != null) {
                    JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
                    totalPages = convertedObject.get("total_pages").getAsInt();
                    JsonArray data = convertedObject.getAsJsonArray("data");
                    for (int i = 0; i < data.size(); i++) {
                        String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                        titles.add(title);
                    }
                }
                in.close();
                startPage++;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }

        }
        Collections.sort(titles);
        return titles.toArray(new String[0]);
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;
        String _substr;
        try {
            _substr = in.nextLine();
        } catch (Exception e) {
            _substr = null;
        }
        
        res = getMovieTitles(_substr);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }
        
        bw.close();
    }
}
	
	
	
	/*public static void main(String[] args) {
		String sub="\"Title\":{\"page\": \"1\",\"per_page\": 10,\"total\": 13,\"total_pages\": 2,\"data\": [{\"Title\": \"Italian Spiderman\",\"Year\": 2007,\"imdbID\": \"tt2705436\"},{\"Title\": \"Superman, Spiderman or Batman\",\"Year\": 2011,\"imdbID\": \"tt2084949\"},{\"Title\": \"Spiderman\",\"Year\": 1990,\"imdbID\": \"tt0100669\"},{\"Title\": \"Spiderman\",\"Year\": 2010,\"imdbID\": \"tt1785572\"},{\"Title\": \"Fighting, Flying and Driving: The Stunts of Spiderman 3\",\"Year\": 2007,\"imdbID\": \"tt1132238\"},{\"Title\": \"Spiderman and Grandma\",\"Year\": 2009,\"imdbID\": \"tt1433184\"},{\"Title\": \"The Amazing Spiderman T4 Premiere Special\",\"Year\": 2012,\"imdbID\": \"tt2233044\"},{\"Title\": \"Amazing Spiderman Syndrome\",\"Year\": 2012,\"imdbID\": \"tt2586634\"},{\"Title\": \"Hollywood's Master Storytellers: Spiderman Live\",\"Year\": 2006,\"imdbID\": \"tt2158533\"},{\"Title\": \"Spiderman 5\",\"Year\": 2008,\"imdbID\": \"tt3696826\"}]\"page\":1";
		String url="https://jsonmock.hackerrank.com/api/movies/search/?"+sub;
		getMovieTitles(url);
	}
}*/