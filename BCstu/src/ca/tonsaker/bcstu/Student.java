package ca.tonsaker.bcstu;

import com.google.gson.annotations.Expose;

public class Student {
	
	@Expose public Result results[];
	
	public class Result{
	
		@Expose public String gender;
		@Expose public Name name;
		
		public class Name{
			@Expose public String title;
			@Expose public String first;
			@Expose public String last;
		}
		
		@Expose public Location location;
		
		public class Location{
			@Expose public String street;
			@Expose public String city;
			@Expose public String state;
			@Expose public String zip;
		}
		
		@Expose public String email;
		@Expose public String username;
		@Expose public String password;
		@Expose public String salt;
		@Expose public String md5;
		@Expose public String shal;
		@Expose public String sha256;
		@Expose public String registered;
		@Expose public String dob;
		@Expose public String phone;
		@Expose public String cell;
		@Expose public String SSN;
		@Expose public Picture picture;
		
		public class Picture{
			@Expose public String large;
			@Expose public String medium;
			@Expose public String thumbnail;
		}
		
		@Expose public String version;
		@Expose public String nationality;
	
	}
	
	@Expose public String seed;
	
	public String toString(){
		return results[0].name.first + " " + results[0].name.last;
	}
	
}
