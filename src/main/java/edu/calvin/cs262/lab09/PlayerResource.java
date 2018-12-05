package edu.calvin.cs262.lab09;

import com.google.api.server.spi.config.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import static com.google.api.server.spi.config.ApiMethod.HttpMethod.GET;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.PUT;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.POST;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.DELETE;

@Api(
        name = "pinder",
        namespace =
        @ApiNamespace(
                ownerDomain = "lab09.cs262.calvin.edu",
                ownerName = "lab09.cs262.calvin.edu",
                packagePath = ""
        ),
        issuers = {
                @ApiIssuer(
                        name = "firebase",
                        issuer = "https://securetoken.google.com/YOUR-PROJECT-ID",
                        jwksUri =
                                "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system"
                                        + ".gserviceaccount.com"
                )
        }
)

public class PlayerResource {
	
	 //////////////// LOGIN /////////////////////////////////////////////////////
      
         // BELOW IS THE PATH TO THE ENDPOINT
    @ApiMethod(path="login/{username}/{password}", httpMethod=GET)
  
    public List<String> selectthelogin(@Named("username") String username,@Named("password") String password) throws SQLException {
    
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> result = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            resultSet = selectLogin(username,password,statement);
            if (resultSet.next()) {
                if (resultSet.getString(1) != null)
                {
      				result.add("valid");
                	
                }
            }
             else {
             	result.add("Invalid");
             }
        } catch (SQLException e) {
            throw(e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
  
        return result;
    }

		// Below here is where we will create our select statement, lets change the paramters of the function..
    private ResultSet selectLogin(String username, String password, Statement statement) throws SQLException {
        return statement.executeQuery(
                 String.format("SELECT * FROM person WHERE loginid='%s' AND password='%s'", username,password)
        );
        // Let's go back to our database to learn how the table structure works
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////// MATCHES /////////////////////////////////////////////////////
    @ApiMethod(path="matches/{id}", httpMethod=GET)
    public List<Matches> selecttheMatches(@Named("id") int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Matches> result = new ArrayList<Matches>();
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            resultSet = selectMatches(id,statement);
            while (resultSet.next()) {
                Matches p = new Matches(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            throw(e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
  
        return result;
    }

    private ResultSet selectMatches(int id, Statement statement) throws SQLException {
        return statement.executeQuery(
                 String.format("SELECT d.name,d.photo,d.breedid FROM matches m JOIN dog d ON m.dogid = d.dogid WHERE m.personid=%d" , id)
        );
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////// PERSON INFORMATION  /////////////////////////////////////////////////////
    @ApiMethod(path="person/{id}", httpMethod=GET)
    public List<Person> selectthePeople(@Named("id") String id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Person> result = new ArrayList<Person>();
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            resultSet = selectPeopleList(id,statement);
            while (resultSet.next()) {
                Person p = new Person(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        Double.parseDouble(resultSet.getString(8)),
                        Integer.parseInt(resultSet.getString(9)),
                        resultSet.getString(10)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            throw(e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
  
        return result;
    }

    private ResultSet selectPeopleList(String id, Statement statement) throws SQLException {
        return statement.executeQuery(
                 String.format("SELECT * FROM person WHERE loginid='%s'", id)
        );
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    ///////////////////////////////CREATE ACCOUNT //////////////////////////////
    @ApiMethod(path="player", httpMethod=POST)
    public Person postPerson(Person player) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
    	
	    	  resultSet = statement.executeQuery("SELECT MAX(personid) FROM person");
	          if (resultSet.next()) {
	            player.setPersonID(resultSet.getInt(1) + 1);
	          } else {
	            throw new RuntimeException("failed to find unique ID...");
	          }
			  insertPlayer(player, statement);
        	 

        } catch (SQLException e) {
            throw (e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return player;
	}
	  /*
     * This function gets the player with the given id using the given JDBC statement.
     */
    private ResultSet selectPlayer(int id, Statement statement) throws SQLException {
        return statement.executeQuery(
                String.format("SELECT * FROM person WHERE personid=%d", id)
        );
	}

    /*
     * This function inserts the given player using the given JDBC statement.
     */
    private void insertPlayer(Person player, Statement statement) throws SQLException {
        statement.executeUpdate(
                String.format("INSERT INTO person (personid,loginid,password) VALUES (%d, '%s', '%s')",
                        player.getpersonID(),
                        player.getLoginID(),
                        player.getPassword()
                )
        );
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////EDIT PROFILE //////////////////////////////
@ApiMethod(path="player/{loginid}", httpMethod=PUT)
    public Person putPlayer(Person player, @Named("loginid") String loginid) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            player.setLoginID(loginid);
            resultSet = selectPlayers(loginid, statement);
            if (resultSet.next()) {
                updatePlayer(player, statement);
            } else {
	            throw new RuntimeException("No LoginID found...");
	          }
        } catch (SQLException e) {
            throw (e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return player;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////

  /*
     * This function gets the player with the given id using the given JDBC statement.
     */
    private ResultSet selectPlayers(String id, Statement statement) throws SQLException {
        return statement.executeQuery(
                String.format("SELECT * FROM person WHERE loginid='%s' ", id)
        );
	}
	
	
 private void updatePlayer(Person player, Statement statement) throws SQLException {
        statement.executeUpdate(
                String.format("UPDATE person SET profilephoto='%s', name='%s', email='%s', location='%s' WHERE loginid='%s'",
                        player.getProfilePhoto(),
                        player.getName(),
                        player.getEmail(),
                        player.getLocation(),
                        player.getLoginID()
                )
        );
    }


 /////////////////// PERSON INFORMATION  /////////////////////////////////////////////////////
    @ApiMethod(path="adoptaPet", httpMethod=GET)
    public List<AdoptPet> adoptaPets() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<AdoptPet> result = new ArrayList<AdoptPet>();
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT dogid, photo, name,sex,breedid FROM dog");
            while (resultSet.next()) {
                AdoptPet p = new AdoptPet(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                  
                );
                result.add(p);
            }
        } catch (SQLException e) {
            throw(e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
  
        return result;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////


  ///////////////////////////////List A Pet //////////////////////////////
    @ApiMethod(path="listpet", httpMethod=POST)
    public ListaPet postDog(ListaPet player) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
	    	 resultSet = statement.executeQuery("SELECT MAX(dogid) FROM dog");
	          if (resultSet.next()) {
	            player.setdogid(resultSet.getInt(1) + 1);	            
	          } else {
	            throw new RuntimeException("failed to find unique ID...");
	          }
			  insertDog(player, statement);
        	 

        } catch (SQLException e) {
            throw (e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return player;
	}
	  /*
     * This function gets the player with the given id using the given JDBC statement.
     */
 private void insertDog(ListaPet player, Statement statement) throws SQLException {
        statement.executeUpdate(
                String.format("INSERT INTO dog (dogid,personid,name,sex,breedid,energylevel,housetrained,size,photo) VALUES (%d,%d,'%s','%s','%s','%s','%s','%s','%s')",
                        player.getdogid(),
                        player.getPersonID(),
                        player.getName(),
                        player.getGender(),
                        player.getBreed(),
                        player.getenergylevel(),
                        player.gethousetrained(),
                        player.getSize(),
                        player.getPhoto()
                     
                     
                )
        );
    }
 
//////////////////////////////////////////////////////////////////////////////////////////////////////


  ///////////////////////////////List A Pet //////////////////////////////
    @ApiMethod(path="newMatch", httpMethod=POST)
    public insertMatch postMatch(insertMatch player) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
	  		statement.executeUpdate(
                String.format("INSERT INTO matches (dogid,personid) VALUES (%d,%d)",
                        player.getdogID(),
                        player.getpersonID()
                		)
        	); 

        } catch (SQLException e) {
            throw (e);
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return player;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////


    
}