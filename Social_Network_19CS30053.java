/*====ASSIGNMENT 0 JAVA PROGRAMMING====
 * Name: Hritaban Ghosh
 * Roll Number: 19CS30053
 *====*****************************====
 */

import java.util.*;

//Base Class
class Node {
	//Data Members
	private int uniq_id;
	private String Name;
	public LinkedList <Node> Link;
	private String Creation_Date;		//Please note that there will be no checks on whether the user has actually entered a date.
	public LinkedList <String> Content;		//Maintained as a Set to avoid duplication of already posted Content but re-posting is allowed.
	
	//Constructor
	public Node(Scanner SC)
	{
		System.out.println("\nEnter a Unique ID: ");
		this.uniq_id=SC.nextInt();
		this.Link=new LinkedList<Node>();
		System.out.println("Enter the Name of the Node: ");
		SC.nextLine();
		this.Name=SC.nextLine();
		System.out.println("Enter the Creation Date of the Node: ");
		this.Creation_Date=SC.nextLine();
		this.Content=new LinkedList<String>();
	}
	//Getter Methods
	public int getUniq_ID () {					
		return this.uniq_id;
	}
	public String get_Name () {					
		return this.Name;
	}
	public String getCreationDate() {					
		return this.Creation_Date;
	}
	public void Display_Content() {
		if(this.Content.size()==0)
		 {
			 System.out.println(this.Name+" has not posted any content!!!");
			 return;
		 }
		 System.out.println("Content posted by "+this.Name+ " are: ");
		 Iterator<String> i = this.Content.iterator();
		 int j=0;
		 while(i.hasNext())
		 {
			 System.out.println((j+1)+") "+i.next());
			 j++;
		 }

	}
	//Setter Methods
	public void add_Content(Scanner SC) {
		SC.nextLine();
		System.out.println("\tEnter the Content to be posted by the Node: ");
		String Post=SC.nextLine();
		if(this.Content.contains(Post))
		{
			System.out.println("\n\nPost "+(this.Content.indexOf(Post)+1)+" has re-posted Successfully!!\n\n");
		}
		else
		{
			(this.Content).add(Post);
			System.out.println("\n\nContent has been created and posted Successfully!!\n\n");
		}
	}
	//Overridden Methods
	public void Display_Node(){}
	public void Display_All_Linked_Nodes(){}
	public void Delete_Node(){}
}






//Derived Class Individual
 class Individual extends Node {
	 //Data Member
	 private String Birthday;			//Please note that there will be no checks on whether the user has actually entered a date.
	 //Constructor
	 public Individual(Scanner SC) {
		 super(SC);
		 System.out.println("Do you want to enter the Birthday of this Individual?\n[Press Y if you want to set the BirthDate else Press any other character]");
		 char choice;
		 choice=SC.next().charAt(0);
		 switch(choice)
		 {
			 case 'Y':
				 System.out.println("Enter Your Birthday: ");
				 SC.nextLine();
				 this.Birthday=SC.nextLine();
				 break;
			 default:
				 this.Birthday="Information Is Not Provided";
		 }
	 }
	 //Getter Methods
	 public String get_BirthDay() {
		 return this.Birthday;
	 }
	 @Override
	 public void Display_Node() {
		 System.out.println("Unique ID: "+this.getUniq_ID());
		 System.out.println("Type: Individual");
		 System.out.println("Name: "+this.get_Name());
		 System.out.println("Birthday: "+this.Birthday);
		 System.out.println("Account Was Created On: "+this.getCreationDate());
		 this.Display_All_Linked_Nodes();
		 this.Display_Content();
	 }
	 @Override
	 public void Display_All_Linked_Nodes() {
		 if(this.Link.size()==0)
		 {
			 System.out.println(this.get_Name()+" has no Links!!!");
			 return;
		 }
		 System.out.println("\nEntities Linked to "+this.get_Name()+ " are: \n");
		 for(int i=0;i<this.Link.size();i++)
		 {
			 if(this.Link.get(i) instanceof Group)
			 {
				 System.out.print("Is a member of Group :\n");
			 }
			 else if(this.Link.get(i) instanceof Business)
			 {
				 Business B=(Business)this.Link.get(i);
				 if(B.is_Owner(this))
				 {
					 System.out.print("Is a Owner of Business :\n");
				 }
				 else if(B.is_Customer(this))
				 {
					 System.out.print("Is a Customer of Business :\n");
				 }
			 }
			 else if(this.Link.get(i) instanceof Organization)
			 {
				 System.out.print("Is associated with Organization :\n");
			 }
			 System.out.println("\tUnique ID: "+this.Link.get(i).getUniq_ID()+"\n\tName: "+this.Link.get(i).get_Name()+"\n");
		 }
	 }
	 @Override
	 public void Delete_Node() {
		 for(int j=0;j<this.Link.size();j++)
		 {
			 if(this.Link.get(j) instanceof Business) {
				 Business Temp=(Business)this.Link.get(j);
				 Temp.remove_Customer(this);
				 Temp.remove_Owner(this);
			 }
			 this.Link.get(j).Link.remove(this);
		 }
		 this.Link.clear();
		 this.Link=null;
		 this.Content.clear();
	 }
 }
 
 
 
 
 
//Derived Class Business
 class Business extends Node {
	 //Data Member
	 private String Latitude;			//Please note that there will be no checks on whether the user has actually entered a Latitude.
	 private String Longitude;			//Please note that there will be no checks on whether the user has actually entered a Longitude.
	 private LinkedList <Individual> Owner;
	 private LinkedList <Individual> Customer;
	 //Constructor
	 public Business(Scanner SC) {
		 super(SC);
		 System.out.println("Enter the 2D Location Co-ordinates Of Your Business:-");
		 System.out.println("Enter the Latitude: ");
		 this.Latitude=SC.nextLine();
		 System.out.println("Enter the Longitude: ");
		 this.Longitude=SC.nextLine();
		 this.Owner=new LinkedList<Individual>();
		 this.Customer=new LinkedList<Individual>();
	 }
	 //Getter Methods
	 public String get_Latitude()			
	 {
		 return this.Latitude;
	 }
	 public String get_Longitude()
	 {
		 return this.Longitude;
	 }
	 public void Display_All_Owners()
	 {
		 if(this.Owner.size()==0)
		 {
			 System.out.println("This Business has no Owner!!!");
			 return;
		 }
		 System.out.println("Owners of "+this.get_Name());
		 for(int i=0;i<this.Owner.size();i++)
		 {
			 System.out.println((i+1)+") Unique ID: "+this.Owner.get(i).getUniq_ID()+"\tName: "+this.Owner.get(i).get_Name());
		 }
	 }
	 public void Display_All_Customers()
	 {
		 if(this.Customer.size()==0)
		 {
			 System.out.println("This Business has no Customers!!!");
			 return;
		 }
		 System.out.println("Customers of "+this.get_Name());
		 for(int i=0;i<this.Customer.size();i++)
		 {
			 System.out.println((i+1)+") Unique ID: "+this.Customer.get(i).getUniq_ID()+"\tName: "+this.Customer.get(i).get_Name());
		 }
	 }
	 //Setter Methods
	 public void add_Owner(Individual I)
	 {
		 this.Owner.add(I);
	 }
	 public void add_Customer(Individual I)
	 {
		 this.Customer.add(I);
	 }
	 public boolean is_Owner(Individual I)
	 {
		 return this.Owner.contains(I);
	 }
	 public boolean is_Customer(Individual I)
	 {
		 return this.Customer.contains(I);
	 }
	 public void remove_Owner(Individual I) {
		 if(this.Owner.contains(I))
		 {
			 this.Owner.remove(I);
		 }
	 }
	 public void remove_Customer(Individual I) {
		 if(this.Customer.contains(I))
		 {
			 this.Customer.remove(I);
		 }
	 }
	 @Override
	 public void Display_Node() {
		 System.out.println("Unique ID: "+this.getUniq_ID());
		 System.out.println("Type: Business");
		 System.out.println("Name : "+this.get_Name());
		 System.out.println("Account Was Created On: "+this.getCreationDate());
		 System.out.println("The business is located at: Latitude: "+this.Latitude+" Longitude: "+this.Longitude);
		 this.Display_All_Owners();
		 this.Display_All_Customers();
		 this.Display_All_Linked_Nodes();
		 this.Display_Content();
	 }
	 @Override
	 public void Display_All_Linked_Nodes() {
		 if(this.Link.size()==0)
		 {
			 System.out.println(this.get_Name()+" has no Links!!!");
			 return;
		 }
		 System.out.println("\nEntities Linked to "+this.get_Name()+ " are: \n");
		 for(int i=0;i<this.Link.size();i++)
		 {
			 if(this.Link.get(i) instanceof Group)
			 {
				 System.out.print("Is a member of Group :\n");
			 }
			 else if(this.Link.get(i) instanceof Individual)
			 {
				 Individual I=(Individual)this.Link.get(i);
				 if(this.is_Owner(I))
				 {
					 System.out.print("Is a Owned by :\n");
				 }
				 else if(this.is_Customer(I))
				 {
					 System.out.print("Has a Customer :\n");
				 }
			 }
			 System.out.println("\tUnique ID: "+this.Link.get(i).getUniq_ID()+"\n\tName: "+this.Link.get(i).get_Name()+"\n");
		 }
	 }
	 @Override
	 public void Delete_Node() {
		 for(int j=0;j<this.Link.size();j++)
		 {
			 this.Link.get(j).Link.remove(this);
		 }
		 this.Link.clear();
		 this.Link=null;
		 this.Content.clear();
		 this.Owner.clear();
		 this.Owner=null;
		 this.Customer.clear();
		 this.Customer=null;
	 }
 }
 
 
 
 //Derived Class Group
 class Group extends Node {
	 //Constructor
	 public Group(Scanner SC) {
		 super(SC);
	 }
	 @Override
	 public void Display_Node() {
		 System.out.println("Unique ID: "+this.getUniq_ID());
		 System.out.println("Type: Group");
		 System.out.println("Name : "+this.get_Name());
		 System.out.println("Account Was Created On: "+this.getCreationDate());
		 this.Display_All_Linked_Nodes();
		 this.Display_Content();
	 }
	 @Override
	 public void Display_All_Linked_Nodes() {
		 if(this.Link.size()==0)
		 {
			 System.out.println(this.get_Name()+" has no Links!!!");
			 return;
		 }
		 System.out.println("\nEntities Linked to "+this.get_Name()+ " are: \n");
		 for(int i=0;i<this.Link.size();i++)
		 {
			 if(this.Link.get(i) instanceof Business)
			 {
				 System.out.print("Has a member Business :\n");
			 }
			 else if(this.Link.get(i) instanceof Individual)
			 {
				 System.out.print("Has a member Individual :\n");
			 }
			 System.out.println("\tUnique ID: "+this.Link.get(i).getUniq_ID()+"\n\tName: "+this.Link.get(i).get_Name()+"\n");
		 }
	 }
	 @Override
	 public void Delete_Node() {
		 for(int j=0;j<this.Link.size();j++)
		 {
			 this.Link.get(j).Link.remove(this);
		 }
		 this.Link.clear();
		 this.Link=null;
		 this.Content.clear();
	 }
 }
 
 
 
 
 //Derived Class Organization
 class Organization extends Node {
	 private String Latitude;			//Please note that there will be no checks on whether the user has actually entered a Latitude.
	 private String Longitude;			//Please note that there will be no checks on whether the user has actually entered a Longitude.
	 //Constructor
	 public Organization(Scanner SC) {
		 super(SC);
		 System.out.println("Enter the 2D Location Co-ordinates Of Your Organization:-");
		 System.out.println("Enter the Latitude: ");
		 this.Latitude=SC.nextLine();
		 System.out.println("Enter the Longitude: ");
		 this.Longitude=SC.nextLine();
	 }
	 //Getter Methods
	 public String get_Latitude()			
	 {
		 return this.Latitude;
	 }
	 public String get_Longitude()
	 {
		 return this.Longitude;
	 }
	 @Override
	 public void Display_Node() {
		 System.out.println("Unique ID: "+this.getUniq_ID());
		 System.out.println("Type: Organization");
		 System.out.println("Name : "+this.get_Name());
		 System.out.println("Account Was Created On: "+this.getCreationDate());
		 System.out.println("The Organization is located at: Latitude: "+this.Latitude+" Longitude: "+this.Longitude);
		 this.Display_All_Linked_Nodes();
		 this.Display_Content();
	 }
	 @Override
	 public void Display_All_Linked_Nodes() {
		 if(this.Link.size()==0)
		 {
			 System.out.println(this.get_Name()+" has no Links!!!");
			 return;
		 }
		 System.out.println("\nEntities Linked to "+this.get_Name()+ " are: \n");
		 for(int i=0;i<this.Link.size();i++)
		 {
			 if(this.Link.get(i) instanceof Individual)
			 {
				 System.out.print("Has a member Individual :\n");
			 }
			 System.out.println("\tUnique ID: "+this.Link.get(i).getUniq_ID()+"\n\tName: "+this.Link.get(i).get_Name()+"\n");
		 }
	 }
	 @Override
	 public void Delete_Node() {
		 for(int j=0;j<this.Link.size();j++)
		 {
			 this.Link.get(j).Link.remove(this);
		 }
		 this.Link.clear();
		 this.Link=null;
		 this.Content.clear();
	 }
 }
 
	
 public class Social_Network_19CS30053 {
	 
	 public static LinkedList <Individual> Individual_List=new LinkedList<Individual>();
	 public static LinkedList <Group> Group_List=new LinkedList<Group>();
	 public static LinkedList <Business> Business_List=new LinkedList<Business>();
	 public static LinkedList <Organization> Organization_List=new LinkedList<Organization>();
	 public static LinkedList <Integer> ID_list=new LinkedList<Integer>();						//List to keep track of Unique IDs which are in use
	 public static Scanner SC = new Scanner(System.in);
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
			
		 
			char choice;
			do
			{
				System.out.println("===========================SOCIAL NETWORK INTERFACE===========================");
				System.out.println("***A Node Can Be An Individual, A Group, A Business Or An Organization***");
				System.out.println("0)To Display All Nodes In The System press '0'");
				System.out.println("1)To Create A New Node press '1'");
				System.out.println("2)To Delete A Node press '2'");
				System.out.println("3)To Search For A Node press '3'");
				System.out.println("4)To Display All Linked Nodes To A Node Of Your Choice press '4'");
				System.out.println("5)To Create And Post Content By A User press '5'");
				System.out.println("6)To Search For Content Posted By Node Of Your Choice press '6'");
				System.out.println("7)To Display All Content Posted By Nodes Linked To A Node Of Your Choice press '7'");
				System.out.println("8)To Link A Node to Another Node press '8'");
				System.out.println("q)To Quit The Program press 'q'");
				System.out.println("===========================*******************===========================");
				System.out.println("\nEnter Your Choice: ");
				choice=SC.next().charAt(0);
				switch(choice)
				{
				case '0':
					if(!Display_All())
					 {
						System.out.println("\n\nTHERE IS NO ONE ON THIS NETWORK!!!\nGo back to the menu and press 1 to add entities.\n\n");
					 }
					break;
				case '1':
				{
					System.out.println("\n\tDo You Want To Create An Individual, A Group, A Business Or An Organization?");
					System.out.println("\tI)To Create An Individual press 'I'");
					System.out.println("\tG)To Create A Group press 'G'");
					System.out.println("\tB)To Create A Business press 'B'");
					System.out.println("\tO)To Create An Organization press 'O'");
					System.out.println("\nEnter Your Choice: ");
					char create;
					create=SC.next().charAt(0);
					switch(create)
					{
					case 'I':
						Individual I=new Individual(SC);
						if(ID_list.contains(I.getUniq_ID()))
						{
							System.out.println("\n\nIndividual Could Not Be Created!!!\nThis is because the unique id you have entered already exists.\nWe are sorry for the inconvinience.\nYou will now be directed to the SOCIAL NETWORK MENU.\n\n");
							break;
						}
						else
						{
							System.out.println("\n\nIndividual Created Successfully!!!\n\n");
							ID_list.add(I.getUniq_ID());
						}
						Individual_List.add(I);
						To_Add_To(I);
						break;
					case 'G':
						Group G=new Group(SC);
						if(ID_list.contains(G.getUniq_ID()))
						{
							System.out.println("\n\nGroup Could Not Be Created!!!\nThis is because the unique id you have entered already exists.\nWe are sorry for the inconvinience.\nYou will now be directed to the SOCIAL NETWORK MENU.\n\n");
							break;
						}
						else
						{
							System.out.println("\n\nGroup Created Successfully!!!\n\n");
							ID_list.add(G.getUniq_ID());
						}
						Group_List.add(G);
						break;
					case 'B':
						Business B=new Business(SC);
						if(ID_list.contains(B.getUniq_ID()))
						{
							System.out.println("\n\nBusiness Could Not Be Created!!!\nThis is because the unique id you have entered already exists.\nWe are sorry for the inconvinience.\nYou will now be directed to the SOCIAL NETWORK MENU.\n\n");
							break;
						}
						else
						{
							System.out.println("\n\nBusiness Created Successfully!!!\n\n");
							ID_list.add(B.getUniq_ID());
						}
						Business_List.add(B);
						To_Add_To(B);
						break;
					case 'O':
						Organization O=new Organization(SC);
						if(ID_list.contains(O.getUniq_ID()))
						{
							System.out.println("\n\nOrganization Could Not Be Created!!!\nThis is because the unique id you have entered already exists.\nWe are sorry for the inconvinience.\nYou will now be directed to the SOCIAL NETWORK MENU.\n\n");
							break;
						}
						else
						{
							System.out.println("\n\nOrganization Created Successfully!!!\n\n");
							ID_list.add(O.getUniq_ID());
						}
						Organization_List.add(O);
						break;
					default:
						System.out.println("\n\nWrong User Input!!!\n\n");
					}
					break;
				}
				case '2':
				{
						System.out.println("\n\tSpecify the Unique ID of the Node You Want To DELETE?");
						int uniq_id;
						uniq_id=SC.nextInt();
						 if(Delete_Node(uniq_id))
						 {
							 System.out.println("\n\nNode Successfully Deleted!!!\n\n");
						 }
						 else
						 {
							 System.out.println("\n\nNode Not Found!!!\n\n");
						 }
						break;
				}
				case '3':
				{
					System.out.println("\n\tDo You Want To Search By Name, Type Or Birthday?");
					System.out.println("\tN)To Search By Name press 'N'");
					System.out.println("\tT)To Search By Type press 'T'");
					System.out.println("\tB)To Search By Birthday press 'B'");
					System.out.println("\nEnter Your Choice: ");
					char search;
					search=SC.next().charAt(0);
					SC.nextLine();
					switch(search)
					{
					case 'N':
					{
						System.out.println("Enter the Name You want to search by: ");
						String name;
						name=SC.nextLine();
						SearchByName(name);
						break;
					}
					case 'T':
					{
						System.out.println("Enter the Type You want to search by: ");
						String type;
						type=SC.nextLine();
						SearchByType(type);
						break;
					}
					case 'B':
					{
						System.out.println("Enter the Birthday You want to search by: ");
						String day;
						day=SC.nextLine();
						SearchByBirthday(day);
						break;
					}
					default:
						System.out.println("\n\nWrong User Input!!!\n\n");
					}
					break;
				}
				case '4':
				{
						System.out.println("\n\tSpecify the Unique ID of the Node Whose Linked Nodes are to be DISPLAYED?");
						int uniq_id;
						uniq_id=SC.nextInt();
						if(!Display_Links(uniq_id))
						 {
							System.out.println("\n\nNode Not Found!!!\n\n");
						 }
						break;
				}
				case '5':
				{
						System.out.println("\n\tSpecify the Unique ID of the Node Who wants to CREATE and POST CONTENT?");
						int uniq_id;
						uniq_id=SC.nextInt();
						if(!Create_Post(uniq_id))
						{
							System.out.println("\n\nNode Not Found!!!\n\n");
						}
						break;
				}
				case '6':
				{
					System.out.println("\n\tSpecify the Unique ID of the Node Whose Contents you want to Search through?");
					int uniq_id;
					uniq_id=SC.nextInt();
					if(!Display_Content(uniq_id))
					{
						System.out.println("\n\nNode Not Found!!!\n\n");
					}
					break;
				}
				case '7':
				{
						System.out.println("\n\tSpecify the Unique ID of the Node?");
						int uniq_id;
						uniq_id=SC.nextInt();
						if(!Display_Contents__Of_Links(uniq_id))
						{
							System.out.println("\n\nNode Not Found!!!\n\n");
						}
						break;
				}
				case '8':
				{
					System.out.println("\nWelcome to the Linking Panel. Here you can Link One Node to Another.\n");
					System.out.println("NOTE 1: You have to enter the Unique ID of both the entities so please kindly have that information with you.\n\tThis is done to ensure that you are added to the right entity and not to an entity with the same name or any other parameter.");
					System.out.println("NOTE 2: You can only associate yourself with existing entities here.\n\tSo if you want to create a new entity and add yourself to it later on,\n\tyou can do so using option 1 to create that entity and then come back to option 8 to associate yourself with it in the SOCIAL NETWORK MENU.\n\n");
					char c;
					int uniq_id1;
					do
					{
						System.out.println("Enter the Unique ID of the entity who is to be the member of another entity: ");
						uniq_id1=SC.nextInt();
						System.out.println("Please wait...while we redirect you to the association menu.\n\n\n");
						if(!LinkingPanel(uniq_id1))
						{
							System.out.println("\n\nNode Not Found!!!\n\n");
						}
						System.out.println("Would you like to Link again?\nPress 'Y' if yes else Press any other key.");
						c=SC.next().charAt(0);
						
					}while(c=='Y');
					break;
				}
				case 'q':
					System.out.println("\n\n\t\tGood Bye,User!!! Have A Nice Day and be sure to visit again!!!");
					break;
				default:
					System.out.println("\n\nWrong User Input!!!\n\n");
				}
			}while(choice !='q');
		}
	 public static boolean Display_All()														//Displays all the Nodes in the Network
	 {
		 int I=Individual_List.size(),G=Group_List.size(),B=Business_List.size(),O=Organization_List.size();
		 if(I==0 && B==0 && G==0 && O==0)
		 {
			 return false;
		 }
		 System.out.println("\n\nTotal Entities In The SOCIAL NETWORK: "+(I+B+G+O)+"\n\n");
		 System.out.println("\tNumber Of Individual(s): "+I);
		 System.out.println("\tNumber Of Group(s): "+G);
		 System.out.println("\tNumber Of Business(es): "+B);
		 System.out.println("\tNumber Of Organization(s): "+O+"\n\n");
		 
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual_List.get(i).Display_Node();
			 System.out.println("\n\n");
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group_List.get(i).Display_Node();
			 System.out.println("\n\n");
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business_List.get(i).Display_Node();
			 System.out.println("\n\n");
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization_List.get(i).Display_Node();
			 System.out.println("\n\n");
		 }
		 return true;
	 }
	 
	 public static void To_Add_To(Node N) {														//Method to Link N to other entities. This method is called during Creation of Nodes and by option 8 indirectly.
		 if(N instanceof Individual)
		 {
			 System.out.println("You can associate with other entities here: ");
			 System.out.println("NOTE 1: You have to enter the Unique ID of the other entity so please kindly have that information with you.\n\tThis is done to ensure that you are added to the right entity and not to an entity with the same name or any other parameter.");
			 System.out.println("NOTE 2: You can only associate yourself with existing entities here.\n\tSo if you want to create a new entity and add yourself to it later on,\n\tyou can do so using option 1 to create that entity and then option 8 to associate yourself with it in the SOCIAL NETWORK MENU.\n\n");
			 char choice;
			 do
			 {
				System.out.println("\tG)To Add this Individual to a Group press 'G'");
				System.out.println("\tB)To Associate this Individual with a Business press 'B'");
				System.out.println("\tO)To Add this Individual to an Organization press 'O'");
				System.out.println("\tq)To Close this Menu press 'q'");
				System.out.println("\nEnter Your Choice: ");
				choice=SC.next().charAt(0);
				switch(choice)
				{
					case 'G':
					{
						int uniq_id;
						System.out.println("Please enter the Unique ID of the Group: ");
						uniq_id=SC.nextInt();
						Group G=null;
						int flag=0;
						for(int i=0;i<Group_List.size();i++)
						{
							if(Group_List.get(i).getUniq_ID()==uniq_id)
							{
								flag=1;
								G=Group_List.get(i);
								break;
							}
						}
						if(flag!=1)
						{
							System.out.println("\n\nThe Unique ID either does not belong to a Group or does not exist. Please Try Again!!!\n\n"+uniq_id);
							break;
						}
						else if(flag==1)
						{
							if(G.Link.contains(N))
							{
								System.out.println("\n\n"+N.get_Name()+" is already a member of "+G.get_Name()+"\n\n");
							}
							else
							{	
								G.Link.add(N);
								N.Link.add(G);
								System.out.println("\n\n"+N.get_Name()+" has been successfully added to "+G.get_Name()+"\n\n");
							}
						}
						break;
					}
					case 'B':
					{
						int uniq_id;
						System.out.println("Please enter the Unique ID of the Business: ");
						uniq_id=SC.nextInt();
						Business B=null;
						int flag=0;
						for(int i=0;i<Business_List.size();i++)
						{
							if(Business_List.get(i).getUniq_ID()==uniq_id)
							{
								flag=1;
								B=Business_List.get(i);
								break;
							}
						}
						if(flag!=1)
						{
							System.out.println("\n\nThe Unique ID either does not belong to a Business or does not exist. Please Try Again!!!\n\n");
							break;
						}
						else if(flag==1)
						{
							if(B.Link.contains(N))
							{
								System.out.println("\n\n"+N.get_Name()+" is already associated with "+B.get_Name()+"\n\n");
							}
							else
							{	
								B.Link.add(N);
								N.Link.add(B);
								
								char c;
								
								do 
								{
									System.out.println("\nDo you want to add the Individual as an Owner or a Customer?\n");
									System.out.println("\tO) To add as an Owner press 'O'");
									System.out.println("\tC) To add as an Customer press 'C'");
									System.out.println("Enter your choice: ");
									c=SC.next().charAt(0);
									switch(c)
									{
									case 'O':
										B.add_Owner((Individual)N);
										System.out.println("\n\n"+N.get_Name()+" has been successfully made the Owner of "+B.get_Name()+"\n\n");
										break;
									case 'C':
										B.add_Customer((Individual)N);
										System.out.println("\n\n"+N.get_Name()+" has successfullly become the Customer of "+B.get_Name()+"\n\n");
										break;
									default:
										System.out.println("Wrong User Input!!!Please try again.");
									}
								}while(c!='C' && c!='O');
							}
						}
						break;
					}
					case 'O':
					{
						int uniq_id;
						System.out.println("Please enter the Unique ID of the Organization: ");
						uniq_id=SC.nextInt();
						Organization O=null;
						int flag=0;
						for(int i=0;i<Organization_List.size();i++)
						{
							if(Organization_List.get(i).getUniq_ID()==uniq_id)
							{
								flag=1;
								O=Organization_List.get(i);
								break;
							}
						}
						if(flag!=1)
						{
							System.out.println("\n\nThe Unique ID either does not belong to a Organization or does not exist. Please Try Again!!!\n\n");
							break;
						}
						else if(flag==1)
						{
							if(O.Link.contains(N))
							{
								System.out.println("\n\n"+N.get_Name()+" is already a member of "+O.get_Name()+"\n\n");
							}
							else
							{	
								O.Link.add(N);
								N.Link.add(O);
								System.out.println("\n\n"+N.get_Name()+" has been successfully added to "+O.get_Name()+"\n\n");
							}
						}
						break;
					}
					case 'q':
						System.out.println("\n\n\nYou will now be re-directed to the previous page.\n\n\n");
						break;
					default:
						System.out.println("Wrong User Input!!!");
						
				}
				 
			 }while(choice!='q');
		 }
		 else if(N instanceof Business)
		 {
			 System.out.println("You can associate with other Groups here: ");
			 System.out.println("NOTE 1: You have to enter the Unique ID of the other Group so please kindly have that information with you.\n\tThis is done to ensure that you are added to the right entity and not to an entity with the same name or any other parameter.");
			 System.out.println("NOTE 2: You can only associate yourself with existing Groups here.\n\tSo if you want to create a new Group and add yourself to it later on,\n\tyou can do so using option 1 to create that Group and then option 8 to associate yourself with it in the SOCIAL NETWORK MENU.\n\n");
			 char choice;
			 do 
			 {
				 System.out.println("Do you want to add this Business to another Group?(Y/N)");
				 System.out.println("Enter your choice: ");
				 choice=SC.next().charAt(0);
				 if(choice=='Y')
				 {
					 int uniq_id;
					 System.out.println("Please enter the Unique ID of the Group: ");
					 uniq_id=SC.nextInt();
					 Group G=null;
					 int flag=0;
					 for(int i=0;i<Group_List.size();i++)
					 {
						if(Group_List.get(i).getUniq_ID()==uniq_id)
						{
							flag=1;
							G=Group_List.get(i);
							break;
						}
					 }
					 if(flag!=1)
					 {
						System.out.println("\n\nThe Unique ID either does not belong to a Group or does not exist. Please Try Again!!!\n\n");
					 }
					 else if(flag==1)
					 {
						if(G.Link.contains(N))
						{
							System.out.println("\n\n"+N.get_Name()+" is already a member of "+G.get_Name()+"\n\n");
						}
						else
						{	
							G.Link.add(N);
							N.Link.add(G);
							System.out.println("\n\n"+N.get_Name()+" has been successfully added to "+G.get_Name()+"\n\n");
						}
					 }
				 }
			}while(choice!='N');
		 }
		 else
		 {
			 System.out.println("\n\nGroups and Organizations are not permitted to be a member of another Entity!!!\n\n");
		 }
	 }
	 
	 public static void SearchByName(String name) {
		 System.out.println("\nIndividuals with name "+name+" :");
		 int flag=0;
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.get_Name().equalsIgnoreCase(name))
			 {
				 System.out.println("\n");
				 Temp.Display_Node();
				 flag=1;
				 System.out.println("\n");
			 }
		 }
		 if(flag==0)
		 {
			 System.out.println("\tNo Individuals with name "+name+" exist in the Network");
		 }
		 flag=0;
		 System.out.println("\n\nGroups with name "+name+" :");
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.get_Name().equalsIgnoreCase(name))
			 {
				 System.out.println("\n");
				 Temp.Display_Node();
				 flag=1;
				 System.out.println("\n");
			 }
		 }
		 if(flag==0)
		 {
			 System.out.println("\tNo Groups with name "+name+" exist in the Network");
		 }
		 flag=0;
		 System.out.println("\n\nBusinesses with name "+name+" :\n\n");
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.get_Name().equalsIgnoreCase(name))
			 {
				 System.out.println("\n");
				 Temp.Display_Node();
				 flag=1;
				 System.out.println("\n");
			 }
		 }
		 if(flag==0)
		 {
			 System.out.println("\n\tNo Businesses with name "+name+" exist in the Network");
		 }
		 flag=0;
		 System.out.println("\n\nOrganizations with name "+name+" :\n\n");
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.get_Name().equalsIgnoreCase(name))
			 {
				 System.out.println("\n");
				 Temp.Display_Node();
				 flag=1;
				 System.out.println("\n");
			 }
		 }
		 if(flag==0)
		 {
			 System.out.println("\tNo Organizations with name "+name+" exist in the Network");
		 }
	 }
	 
	 public static void SearchByType(String type) {
		 
		 if(type.equalsIgnoreCase("individual"))
		 {
			 if(Individual_List.size()==0)
			 {
				 System.out.println("\nNo Individuals with exist in the Network\n\n");
			 }
			 else
			 {
				 for(int i=0;i<Individual_List.size();i++)
				 {
					 Individual Temp=Individual_List.get(i);
					 Temp.Display_Node();
				 }
			 }
		 }
		 else if(type.equalsIgnoreCase("group"))
		 {
			 if(Group_List.size()==0)
			 {
				 System.out.println("\nNo Groups with exist in the Network\n\n");
			 }
			 else
			 {
				 for(int i=0;i<Group_List.size();i++)
				 {
					 Group Temp=Group_List.get(i);
					 Temp.Display_Node();
				 }
			 }
		 }
		 else if(type.equalsIgnoreCase("business"))
		 {
			 if(Business_List.size()==0)
			 {
				 System.out.println("\nNo Businesss with exist in the Network\n\n");
			 }
			 else
			 {
				 for(int i=0;i<Business_List.size();i++)
				 {
					 Business Temp=Business_List.get(i);
					 Temp.Display_Node();
				 }
			 }
		 }
		 else if(type.equalsIgnoreCase("organisation") || type.equalsIgnoreCase("organization"))
		 {
			 if(Organization_List.size()==0)
			 {
				 System.out.println("\nNo Organizations with exist in the Network\n\n");
			 }
			 else
			 {
				 for(int i=0;i<Organization_List.size();i++)
				 {
					 Organization Temp=Organization_List.get(i);
					 Temp.Display_Node();
				 }
			 }
		 }
		 else
		 {
			 System.out.println("No such type exists!!Please check your type name.");
		 }
	 }
	 
	 public static void SearchByBirthday(String birthday) {
		 System.out.println("Individuals with Birthday: "+birthday+"\n\n");
		 int flag=0;
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.get_BirthDay().equalsIgnoreCase(birthday))
			 {
				 Temp.Display_Node();
				 flag=1;
			 }
		 }
		 if(flag==0)
		 {
			 System.out.println("\nNo Individuals with Birthday: "+birthday+ " exist in the Network\n\n");
		 }
	 }
	
	 public static boolean Display_Links(int uniq_id) {
		
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 
				 Temp.Display_All_Linked_Nodes();
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 
				 Temp.Display_All_Linked_Nodes();
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 
				 Temp.Display_All_Linked_Nodes();
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 
				 Temp.Display_All_Linked_Nodes();
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean Create_Post(int uniq_id) {
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.add_Content(SC);
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.add_Content(SC);
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.add_Content(SC);
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.add_Content(SC);
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean Display_Content(int uniq_id) {
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.Display_Content();
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.Display_Content();
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.Display_Content();
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 Temp.Display_Content();
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean Display_Contents__Of_Links(int uniq_id) {
		 
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 System.out.println("\n\nContent Posted by Nodes Linked to "+Temp.get_Name()+ " are: \n\n");
				 for(int j=0;j<Temp.Link.size();j++)
				 {
					 Temp.Link.get(j).Display_Content();
				 }
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 System.out.println("\n\nContent Posted by Nodes Linked to "+Temp.get_Name()+ " are: \n\n");
				 for(int j=0;j<Temp.Link.size();j++)
				 {
					 Temp.Link.get(j).Display_Content();
				 }
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 System.out.println("\n\nContent Posted by Nodes Linked to "+Temp.get_Name()+ " are: \n\n");
				 for(int j=0;j<Temp.Link.size();j++)
				 {
					 Temp.Link.get(j).Display_Content();
				 }
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 System.out.println("\n\nContent Posted by Nodes Linked to "+Temp.get_Name()+ " are: \n\n");
				 for(int j=0;j<Temp.Link.size();j++)
				 {
					 Temp.Link.get(j).Display_Content();
				 }
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean LinkingPanel(int uniq_id1){											//Method to Link two Nodes at any point of time explicitly by the user.
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id1)
			 {
				 To_Add_To(Temp);
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id1)
			 {
				 To_Add_To(Temp);
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id1)
			 {
				 To_Add_To(Temp);
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id1)
			 {
				 To_Add_To(Temp);
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean Delete_Node(Integer uniq_id)
	 {
		 for(int i=0;i<Individual_List.size();i++)
		 {
			 Individual Temp=Individual_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 ID_list.remove(uniq_id);
				 Temp.Delete_Node();
				 Individual_List.remove(Temp);
				 Temp=null;
				 return true;
			 }
		 }
		 for(int i=0;i<Group_List.size();i++)
		 {
			 Group Temp=Group_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 ID_list.remove(uniq_id);
				 Temp.Delete_Node();
				 Group_List.remove(Temp);
				 Temp=null;
				 return true;
			 }
		 }
		 for(int i=0;i<Business_List.size();i++)
		 {
			 Business Temp=Business_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 ID_list.remove(uniq_id);
				 Temp.Delete_Node();
				 Business_List.remove(Temp);
				 Temp=null;
				 return true;
			 }
		 }
		 for(int i=0;i<Organization_List.size();i++)
		 {
			 Organization Temp=Organization_List.get(i);
			 if(Temp.getUniq_ID()==uniq_id)
			 {
				 ID_list.remove(uniq_id);
				 Temp.Delete_Node();
				 Organization_List.remove(Temp);
				 Temp=null;
				 return true;
			 }
		 }
		 return false;
	 }
 }