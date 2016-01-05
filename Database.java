

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database
{
	    
    
        static final String JDBC_MYSQL_DRIVER="org.gjt.mm.mysql.Driver";
	static final String JDBC_MYSQL_URL="jdbc:mysql://*************";
	static final String userName = "mrios3";
	static final String password="Cosc*8m6r";
	private static Connection connection=null;
        
	public static void main(String[] args) throws Exception
	{
		
            
                Class.forName(JDBC_MYSQL_DRIVER).newInstance();
                connection =DriverManager.getConnection(JDBC_MYSQL_URL,userName,password);
		Statement queryStatement = connection.createStatement();
		String query="USE mrios3db";
                queryStatement.executeQuery(query);
                
                String command = "A";
                String cont = "Y";
                 
                String contin = "Y";
                
                Scanner reader = new Scanner(System.in);
                
               
                    
                while (!contin.equals("N")){
		    
                
                    System.out.println("HELLO AND WELCOME");
                    System.out.println("Enter Q to Query the database or M to modify it");
                    command = reader.nextLine();
                
                    if (command.equals("Q")){
                        
                        String commandQ = "A";
                    
                        String cont1 = "Y";
                        while (!cont1.equals("N")){
                    
                            System.out.println("Which Query would you like to run??");
                            System.out.println("M to see all Members");
                            System.out.println("O to see all Offical Members");
                            System.out.println("N to see all New Members");
                            System.out.println("P to see all Potential Members");
                            System.out.println("B to see all Members wishing to be baptized");
                            System.out.println("F to see Family Relationships");
                            System.out.println("C to see all Classes being offered");
                            System.out.println("CT to see classes that have been and will be taken by New Members");
                            System.out.println("T to see who is Teaching which classes");
                            System.out.println("MIN to see all Ministries and there members");
                            System.out.println("MINL to see all Ministries and there Leaders");
                            System.out.println("MINP to see all Ministries and members who hold special positions on them");
                            System.out.println("S to see which skills Offical Members posses");
                    
                            commandQ = reader.nextLine();
               
                    
                    
                            if (commandQ.equals("M")){
                                query = "select * from MEMBER ORDER BY lName;";
                                ResultSet result = queryStatement.executeQuery(query);
                                System.out.println(String.format("%-10s %-10s %s" , "Last Name", "First Name", "Phone Number" ));
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"), result.getString("phone")));
                                        //System.out.println(result.getString("fName") + "  " + result.getString("lName"));
                                    }
                            }                      
                            else if (commandQ.equals("O")){
                                System.out.println(String.format("%-10s %-10s %s" , "Last Name", "First Name", "Phone Number" ));
                                query = "SELECT * FROM MEMBER WHERE Mem_ID IN (SELECT Mem_ID FROM OFFICIAL_MEM) ORDER BY lName;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"), result.getString("phone")));
                                    }
                            } 
                            else if (commandQ.equals("N")){
                                System.out.println(String.format("%-10s %-10s %s" , "Last Name", "First Name", "Phone Number" ));
                                query = "SELECT * FROM MEMBER WHERE Mem_ID IN (SELECT Mem_ID FROM NEW_MEMBER) ORDER BY lName;;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"), result.getString("phone")));
                                    }
                            }
                            else if (commandQ.equals("P")){
                                System.out.println(String.format("%-10s %-10s %s" , "Last Name", "First Name", "Phone Number" ));
                                query = "SELECT * FROM MEMBER WHERE Mem_ID NOT IN (SELECT Mem_ID FROM OFFICIAL_MEM UNION (SELECT Mem_ID FROM NEW_MEMBER)) ; ";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"), result.getString("phone")));
                                    }
                           }
                           else if (commandQ.equals("F")){
                                System.out.println(String.format("%-10s %-10s %-10s %-10s %s" , "Last Name", "First Name", "Last Name", "First Name", "Relationship"));
                                query = "SELECT * FROM MEMBER M, MEMBER M2, FAM_MEMBER WHERE M.MEM_ID = MEM_ID1 AND M2.MEM_ID = MEM_ID2 ORDER BY M.lName;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %-10s %-10s %s", result.getString("M.lName"), result.getString("M.fName"),result.getString("M2.lName"), result.getString("M2.fName"),result.getString("Relationship")));
                                    }
                           }
                           else if (commandQ.equals("B")){
                                String query1="SELECT * FROM MEMBER where baptized='Yes'";
                                ResultSet result = queryStatement.executeQuery(query1);
                                System.out.println(String.format("%-10s %-10s %s" , "Last Name", "First Name", "Last Name", "Phone Number"));
                                while(result.next())
                                {
                                    System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"),result.getString("phone")));
                                }
                            }
                            else if (commandQ.equals("C")){
                                System.out.println(String.format("%-20s %s" , "Course", "Date"));
                                query = "SELECT * FROM SECTION ORDER BY S_date; ";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-20s %s", result.getString("Co_name"), result.getString("S_date")));
                                    }
                            }
                            else if (commandQ.equals("CT")){
                                System.out.println(String.format("%-10s %-10s %-20s %s", "Last Name", "First Name", "Course Name", "Date of Class"));
                                query = "SELECT * FROM TAKES T, SECTION S, MEMBER M WHERE M_ID = Mem_ID AND S_ID = Sec_ID ORDER BY lName;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %-20s %s", result.getString("lName"), result.getString("fName"), result.getString("Co_name"), result.getString("S_date")));
                                    }
                            }
                            else if (commandQ.equals("T")){
                                System.out.println(String.format("%-10s %-10s %-20s %s", "Last Name", "First Name", "Course Name", "Date of Class"));
                                query = "SELECT * FROM TEACHES T, SECTION S, MEMBER M WHERE T_ID = Mem_ID AND SECID = Sec_ID ORDER BY lName;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %-20s %s", result.getString("lName"), result.getString("fName"), result.getString("Co_name"), result.getString("S_date")));
                                    }
                            }
                            else if (commandQ.equals("MIN")){
                                System.out.println(String.format("%-25s %-10s %s", "Ministry Name", "Last Name", "First Name"));
                                query = "SELECT * FROM MEMBER, JOINED WHERE Off_ID = Mem_ID ORDER BY MJName ASC;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-25s %-10s %s", result.getString("MJName"), result.getString("lName"), result.getString("fName")));
                                    }

                            }
                            else if (commandQ.equals("MINL")){
                                System.out.println("Ministry Leaders");
                                System.out.println(String.format("%-25s %-10s %s", "Ministry Name", "Last Name", "First Name"));
                                query = "SELECT * FROM MINISTRY, MEMBER WHERE Lead_ID = Mem_ID ORDER BY Min_Name ASC;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                {
                                    System.out.println(String.format("%-25s %-10s %s", result.getString("Min_Name"), result.getString("lName"), result.getString("fName")));
                                }     
                            }
                            else if (commandQ.equals("MINP")){
                                System.out.println("Ministry Positions");
                                System.out.println(String.format("%-25s %-10s %-10s %s", "Ministry Name", "Position", "Last Name", "First Name"));
                                query = "SELECT * FROM MEMBER, POSTION P WHERE M_ID = Mem_ID ORDER BY P.MName ASC;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-25s %-10s %-10s %s", result.getString("P.MName"), result.getString("Position"), result.getString("lName"), result.getString("fName")));
                                    }     
                            }
                            else if (commandQ.equals("S")){
                                System.out.println(String.format("%-10s %-10s %s", "Last Name", "First Name", "Skill"));
                                query = "SELECT * FROM HAS_SKILL, MEMBER WHERE OffID = MEM_ID ORDER BY lName ASC;";
                                ResultSet result = queryStatement.executeQuery(query);
                                while(result.next())
                                    {
                                        System.out.println(String.format("%-10s %-10s %s", result.getString("lName"), result.getString("fName"), result.getString("SName")));
                                    }
                        
                            }
                            else{
                                System.out.println("Sorry that is not a command please try again");
                            }
                
                            System.out.println("Would you like to run another Query (Y/N)");
                            cont1 = reader.nextLine();    
                        }
                    }
                 
                    else if (command.equals("M")){
                    
                        String commandM = "A";
                    
                        String cont2 = "Y";
                        while (cont2.equals("Y")){
               
                            System.out.println("How would you like to modify the database??");
                            System.out.println("I to Insert information");
                            System.out.println("D to Delete");
                                       

                            System.out.println("------------------------------------");
                            System.out.println("Please enter a command number ");
                            System.out.println("------------------------------------");
                            System.out.print("->");
		
                            commandM = reader.nextLine();
                        
                            switch (commandM){
                                case "I":
                                case "i":
                                    insertReady();
                                    break;
				case "D":
				case "d":
					delete();
					break;
				/*case "3":
					System.out.println("Done");
					System.exit(1);
					break;*/
				default : System.out.println("Sorry that is not a valid command, please select again");
                                          System.out.println("------------------------------");
                                          commandM = reader.nextLine();;
                                          break;		
	
                    
                    
                        }
                     
                          System.out.println("Would you like to further modify the database (Y/N)");
                        cont2 = reader.nextLine();    
                        }
                    }
                    else{
                        System.out.println("Sorry that is not a valid command, please select again");
                        command = reader.nextLine();
                    }
               }     
           
               System.out.println("Would you like to do anything further with the database(Y/N)");
               contin = reader.nextLine();
         
        }       
    
        
    public static void insertReady() throws SQLException{
		
        Statement queryStatement = connection.createStatement();
		      
    System.out.println("What should be inserted into the tabel?");
    System.out.println("-----------------------------------------------------------");
		
    String insert = "";
    Scanner keyboard = new Scanner(System.in);
    System.out.println("MEMBER, FAM_MEMBER, RHF, NEW_MEMBER, OFFICIAL_MEM, COURSE, SECTION, PREREQ, SKILL, MINISTRY, POSTION");
		
    int i1=0;
    String query0="select * from MEMBER;";
    ResultSet result1 = queryStatement.executeQuery(query0);
    while(result1.next())
    {
        //int i=0;
        i1= result1.getInt("Mem_ID");
    }
        //System.out.println(i1);
        i1 = i1+1;
                
        String comm = keyboard.nextLine(); 
        if (comm.equals("MEMBER")){
                 
                System.out.print("Please Enter the Members First Name ");
                String fName = keyboard.nextLine();
                System.out.print("Please Enter the Members Last Name ");
                String lName = keyboard.nextLine();
                System.out.print("Please Enter the Members middle Name ");
                String mName = keyboard.nextLine();
                if (mName.isEmpty())
                mName = "NULL";
            System.out.print("Please Enter the Members date of birth ");
            String DOB = keyboard.nextLine();
                if (DOB.isEmpty())
                    DOB = "NULL";
            System.out.print("Please Enter the Members street ");
            String street = keyboard.nextLine();
                if (street.isEmpty())
                    street = "NULL";
            System.out.print("Please Enter the Members state ");
            String state = keyboard.nextLine();
                if (state.isEmpty())
                   state = "NULL";
            System.out.print("Please Enter the Members city ");
            String city = keyboard.nextLine();
                if (city.isEmpty())
                     city = "NULL";
            System.out.print("Please Enter the Members zip ");
            String zip = keyboard.nextLine();
                        if (zip.isEmpty())
                            zip = "NULL";
            System.out.print("Please Enter the Members email ");
            String email = keyboard.nextLine();
                        if (email.isEmpty())
                            email = "NULL";
            System.out.print("Please Enter the Members phone ");
            String phone = keyboard.nextLine();
                        if (phone.isEmpty())
                            phone = "NULL";
            System.out.print("Would the member wish to be baptized?? (Yes/No) ");
            String bap = keyboard.nextLine();
                    if (bap.isEmpty())
                            bap = "NULL";
            insert = ("'"+i1+"', "+"'"+fName+"', "+"'"+mName+"', "+"'"+lName+"', "+"'"+DOB+"', "+"'"+street+"', "+"'"+state+
                            "', "+"'"+city+"', "+"'"+zip+"', "+"'"+email+"', "+"'"+phone+"', "+"'"+bap+"'");
            System.out.println(insert);
            
            
		
            String query2="insert into "+comm+" values ("+insert+");";
		        
            queryStatement.executeUpdate(query2);
            System.out.println("insert into values : "+insert);
	
            /*System.out.println("Does this person have any family members in the church(Yes/No)");
            comm = keyboard.nextLine();
            if (comm.equals("Y")){
            
                System.out.println("Please enter the family members first name");
                String fName2 = keyboard.nextLine();
                System.out.println("Please enter the family members Last name");
                String lName2 = keyboard.nextLine();
                System.out.println("Please enter the family members relationship i.e. Mother/Daughter");
                String relationship = keyboard.nextLine();

                String query3="SELECT Mem_ID FROM MEMBER WHERE fName = '"+fName2+"' AND lName = '"+lName2+"';";
		//int MID = 
                //queryStatement.executeUpdate(query3);
                ResultSet result2 = queryStatement.executeQuery(query3);
                i1= result2.getInt("Mem_ID");
                System.out.println(String.format("Mem_ID"));
                //System.out.println(MID);
               // query3="insert into FAM_MEMBER values ('"+i1+"', '" + MID + "', '"+relationship+"');";
		//queryStatement.executeUpdate(query3);
                
                System.out.println("Does this person have any more family members in the church(Yes/No)");
                comm = keyboard.nextLine();
                
            }*/
            
            System.out.println("Is this a New (N), Official (O) or Potential Member (P)");
            comm = keyboard.nextLine(); 
            System.out.println(i1);
            
                if (comm.equals("N")){
                
                    String query3="insert into NEW_MEMBER values ('"+i1+"', '0');";
                    queryStatement.executeUpdate(query3);                
                }
            
                else if (comm.equals("O")){
                
                    System.out.println("When did they become an Official Member (YYYY-MM-DD");
                    String date = keyboard.nextLine();
                    String query3="insert into OFFICIAL_MEM values ('"+i1+"', '" + date+ "');";
                    queryStatement.executeUpdate(query3);                
                }
            
            
                }   
         else{

            	System.out.println("What should be inserted into the tabel?");
		System.out.println("-----------------------------------------------------------");
		
		//Scanner keyboard = new Scanner(System.in);
		System.out.println("MEMBER, FAM_MEMBER, RHF, NEW_MEMBER, OFFICIAL_MEM, COURSE, SECTION, PREREQ, SKILL, MINISTRY,POSTION");
		
		System.out.println("----------------------------------------------------------");
		System.out.print("->");
		String tableName = keyboard.next();
		String name = tableName.toUpperCase(); // table name
		System.out.println("--------------Insert data---------------");
		System.out.print("->");
		Scanner keyboard1 = new Scanner(System.in);
		String insertData = "";
		insertData = keyboard1.nextLine();
		
		String query2="insert into "+name+" values ("+insertData+")";
		queryStatement.executeUpdate(query2);
		System.out.println("insert into values : "+insertData);
		
		query0="select * from MEMBER";
		ResultSet result = queryStatement.executeQuery(query0);
		while(result.next())
		{
			int i=0;
			System.out.println(result.getString(++i));
		}
		//disPlay();
	}//insertReady
        
        
        
        }
	public static void delete() throws SQLException{
	 Statement queryStatement = connection.createStatement();	
            System.out.println("What should be deleted into the tabel?");
        System.out.println("-----------------------------------------------------------");
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("MEMBER, FAM_MEMBER, RHF, NEW_MEMBER, OFFICIAL_MEM, COURSE, SECTION, PREREQ, SKILL, MINISTRY,POSTION");
		
		System.out.println("----------------------------------------------------------");
		System.out.print("->");
		String tableName = keyboard.next();
		String name = tableName.toUpperCase(); // table name
		System.out.println("--------------delete date(primary key value)---------------");
		System.out.print("->");
		Scanner keyboard1 = new Scanner(System.in);
		String deleteData = "";
		deleteData = keyboard1.nextLine(); 
		
		String query1="desc "+ name;
		ResultSet col = queryStatement.executeQuery(query1);
		
		String PKname = "";

		while(col.next())
		{
			if("PRI".equals(col.getString(4))){	// key
				PKname = col.getString(1);	// field
				break;
			}
		}
		
		
		String query2="delete from "+name+" where "+PKname+ "="+deleteData;
		queryStatement.executeUpdate(query2);
		
		
	}// delete()
    
    } 

                
                
        
        
        
