package org.jaxrs.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
	@SuppressWarnings("unchecked")
	public List<User> getAllIsers() {
		List<User> userList = null;
		File file = new File("users.dat");
		if(!file.exists()) {
			userList = new ArrayList<User>();
			userList.add(new User(1,"Md. Ashik Ali Khan","Team Lead"));
			userList.add(new User(2,"Md. Ashik Ali Khan","Project Manager"));
			userList.add(new User(3,"Md. Ashik Ali Khan","Analyst Programmer"));
			userList.add(new User(4,"Md. Ashik Ali Khan","Senior Officer(Banking Software)"));
			userList.add(new User(5,"Md. Ashik Ali Khan","Programmer"));
			userList.add(new User(6,"Md. Ashik Ali Khan","System Engineer"));
			saveUserList(userList);
		}
		else {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				userList = (List<User>) ois.readObject();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return userList;
	}
	
	public User getUser(int id) {
		
		for(User user : getAllIsers()) {
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
	
	public int addUser(User userToAdd) {
		boolean userExists = false;
		
		List<User> userList = getAllIsers();
		
		for(User user : userList) {
			
			if(user.getId()==userToAdd.getId()) {
				userExists = true;
			}
		}
		
		if(!userExists) {
			userList.add(userToAdd);
			saveUserList(userList);
			return 1;
		}
		
		return 0;
	}
	
	public int updateUser(User userToUpdate) {
		
		List<User> userList = getAllIsers();
		
		for(User user : userList)
		{
			if(user.getId()==userToUpdate.getId()) {
				int index = userList.indexOf(user);
				userList.set(index, userToUpdate);
				saveUserList(userList);
				return 1;
			}
		}
		
		return 0;
	}
	
	public int deleteUser(int id) {
		
		List<User> userList = getAllIsers();
		
		for(User user : userList)
		{
			if(user.getId()==id) {
				int index = userList.indexOf(user);
				userList.remove(index);
				saveUserList(userList);
				return 1;
			}
		}
		
		return 0;
	}
	
	private void saveUserList(List<User> userList) {
		File file = new File("users.dat");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		userDao.getAllIsers();		
	}

}
