package household.enumeration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logic.Bean.Input;

// this class/pojo will provide a "name" of a person to expenses menu and will be copy to an external file
public class Persons implements Serializable {
	private static List<Persons> names = new ArrayList<>();
	private static List<Persons> names2 = new ArrayList<>();
	private static List<Persons> printNames = new ArrayList<>();
	private String name;

	public Persons() {
		// TODO Auto-generated constructor stub
	}

	public Persons(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	// method will add and copy a new name to the name.obj file
	public void addName(String PersonName) {

		Persons person = new Persons(PersonName);
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("C:\\Users\\fuckyomama\\Desktop\\household\\data\\names.obg"));
			names = (java.util.List<Persons>) ois.readObject();
			names.add(person);
			ois.close();

		} catch (Exception v) {

		}

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("C:\\Users\\fuckyomama\\Desktop\\household\\data\\names.obg"));) {
			out.writeObject(names);
			out.close();
		} catch (IOException r) {

		}

	}

	// method will extract all names from the name.obj file and insert all
	// values to a new arrayList to be used to display the values in the name
	// box at expenses menu
	public List<Persons> printNames() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("C:\\Users\\fuckyomama\\Desktop\\household\\data\\names.obg"));
			printNames = (java.util.List<Persons>) ois.readObject();

			ois.close();
		} catch (Exception v) {

		}
		return printNames;
	}
}
