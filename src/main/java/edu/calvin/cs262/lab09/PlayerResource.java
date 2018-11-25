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
	////////////// SELECT ALL THE DOGS ///////////////////
    @ApiMethod(path="dogs", httpMethod=GET)
    public List<Dogs> selecttheDogs() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Dogs> result = new ArrayList<Dogs>();
        try {
            connection = DriverManager.getConnection(System.getProperty("cloudsql"));
            statement = connection.createStatement();
            resultSet = selectDogs(statement);
            while (resultSet.next()) {
                Dogs p = new Dogs(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(6)),
                        Float.valueOf(resultSet.getString(7)),
                        Integer.parseInt(resultSet.getString(8))
                        
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

    private ResultSet selectDogs(Statement statement) throws SQLException {
        return statement.executeQuery(
                "SELECT * FROM dog"
        );
    }
    ///////////////////////////////////////////////////////////////////////////
    
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
                        Integer.parseInt(resultSet.getString(1))
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
                 String.format("SELECT dogid FROM matches WHERE personid=%d", id)
        );
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////// PEOPLE  /////////////////////////////////////////////////////
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
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        Double.parseDouble(resultSet.getString(8)),
                        Integer.parseInt(resultSet.getString(9))
                        
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
                 String.format("SELECT * FROM person WHERE loginid=%s", id)
        );
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    
    

}
