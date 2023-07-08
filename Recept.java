import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class Recept {

	private JFrame frame;
	private Box verticalBoxIngredients;
	private Box verticalBoxRecepts;
	private ArrayList<String> ingredients = new ArrayList<>();
	private ArrayList<Food> recepty = new ArrayList<>();
	private JTextField pridajIngredientTextField;
	private JTextField odstranIngredientTextField;
	private Food food;
	private JTextField pridajReceptTextField;
	private JTextField odstranReceptTextField;
	private ArrayList<String> MojeIngredienty = new ArrayList<>();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recept window = new Recept();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Recept() {
		initialize();
	}

	/**
	 * my helper methods methods
	 */
	private void addInitialValues() {
	//pre ingredienty 
	ingredients.addAll(Arrays.asList("Paprika", "Smotana", "Mäso", "Ryža"));
	Collections.sort(ingredients);
	printIngredients();
	
	//pre recepty
	vytvorJedlo("Paprika na smotane", "Paprika", "Smotana", "Mäso", "Ryža");
	}

	//----------buttons ingredients-----------
	private void addIngredient(String ingredient) {
		ingredients.add(ingredient);
		Collections.sort(ingredients);
		printIngredients();
	}
	private void removeIngredient(String ingredient) {
		ingredients.remove(ingredient);
		printIngredients();
	}
	private void removeAllIngredients() {
		ingredients.clear();
		printIngredients();
	}
	//------------------------------------------
	
	private void addCheckbox(String content) {
        JCheckBox checkbox = new JCheckBox(content);
        checkbox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	ktoreIngredientyMam();
                } else {
                	ktoreIngredientyMam();
                }
            }
        });
        verticalBoxIngredients.add(checkbox);
    }
	private void printIngredients() {
		//clear vertical box
		try {
			verticalBoxIngredients.removeAll();
			verticalBoxIngredients.revalidate();
			verticalBoxIngredients.repaint();
			} catch (Exception a) {
				System.out.println(a);
			}
        //-----------------------
        
        for (int i = 0; i < ingredients.size(); i++) {
			addCheckbox(ingredients.get(i));
        }		
        ktoreIngredientyMam();
	}
	
	
	//---recepty------------------------------------------
	
	//vytvaranie oobjektov a vkladanie to ArrayListu--------------------
    private void vytvorJedlo(String jedlo, String... ingredientyNaRecept ) {
	   ArrayList<String> foodIngredients = new ArrayList<>();
	   foodIngredients.addAll(Arrays.asList(ingredientyNaRecept));
	
	   food = new Food(jedlo, foodIngredients);
	   recepty.add(food);
	   Collections.sort(recepty, new PersonNameComparator());
	   printRecepts();
	}
    
    //--------------buttons recepty-------------------------------------------

	private void removeRecept(String ingredientName) {
	    for (Food food : recepty) {
	        if (food.getName().equals(ingredientName)) {
	            recepty.remove(food);
	            break;
	        }
	    }
	    printRecepts();
	}
	
	private void removeAllRecepts() {
		recepty.clear();
		printRecepts();
	}
	//--------------printer-------------------------------------------
	private void printRecepts() {
		//clear vertical box
		try {
		verticalBoxRecepts.removeAll();
		verticalBoxRecepts.revalidate();
		verticalBoxRecepts.repaint();
		} catch (Exception a) {
			System.out.println(a);
		}
        //-----------------------
        JLabel label;
        for (int i = 0; i < recepty.size(); i++) {
        	label = new JLabel(recepty.get(i).getName());
        	label.setToolTipText(String.join(", ", recepty.get(i).getIngredients()));
    		verticalBoxRecepts.add(label);
        }
        ktoreIngredientyMam();
	}
	
	//-------------na ktore jedla mas ingredienty-----------
	
	private void ktoreIngredientyMam() {
		MojeIngredienty.clear();
		for (Component component : verticalBoxIngredients.getComponents()) {
		        JCheckBox checkBox = (JCheckBox) component;
		        if (checkBox.isSelected()) {
		        	MojeIngredienty.add(checkBox.getText());
		        }
		}
		System.out.print(MojeIngredienty);
		zvyrazniDostupneRecepty();
	}
	
	private void zvyrazniDostupneRecepty() {
		
		List<String> textArray;
		for (Component component : verticalBoxRecepts.getComponents()) {
	        JLabel label = (JLabel) component;
	        label.setForeground(Color.RED);
	        textArray = Arrays.asList(label.getToolTipText().split(","));
	        
	        for (int i = 0; i < textArray.size(); i++) {
	            String element = textArray.get(i);
	            textArray.set(i, element.trim());
	        }
	        Collections.sort(textArray);
	        System.out.print(textArray);
	        if (MojeIngredienty.containsAll(textArray)) {
				label.setForeground(Color.GREEN);
			}
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 401, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelIngredienty = new JLabel("<html>Označ ingredienty<br/> ktoré máš:</html>");
		labelIngredienty.setHorizontalAlignment(SwingConstants.LEFT);
		labelIngredienty.setForeground(new Color(0, 0, 0));
		labelIngredienty.setFont(new Font("Arial", Font.BOLD, 14));
		labelIngredienty.setBounds(26, 7, 155, 52);
		frame.getContentPane().add(labelIngredienty);
		
		JLabel labelRecepty = new JLabel("<html>Recepty ktoré<br/>vieš pripraviť :</html>");
		labelRecepty.setHorizontalAlignment(SwingConstants.LEFT);
		labelRecepty.setForeground(Color.BLACK);
		labelRecepty.setFont(new Font("Arial", Font.BOLD, 14));
		labelRecepty.setBounds(210, 7, 155, 52);
		frame.getContentPane().add(labelRecepty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 68, 155, 163);
		frame.getContentPane().add(scrollPane);
		
		verticalBoxIngredients = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBoxIngredients);
		verticalBoxIngredients.setBorder(null);
		
		pridajIngredientTextField = new JTextField();
		pridajIngredientTextField.setBounds(26, 241, 155, 19);
		frame.getContentPane().add(pridajIngredientTextField);
		pridajIngredientTextField.setColumns(10);
		
		JButton pridatIngredient = new JButton("Pridaj ingredient!");
		pridatIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = pridajIngredientTextField.getText();
				if (text.isEmpty()) {
					return;
				}
				addIngredient(text.substring(0, 1).toUpperCase() + text.substring(1));
				pridajIngredientTextField.setText(null);
			}
		});
		pridatIngredient.setBounds(26, 262, 155, 21);
		frame.getContentPane().add(pridatIngredient);
		
		odstranIngredientTextField = new JTextField();
		odstranIngredientTextField.setColumns(10);
		odstranIngredientTextField.setBounds(26, 317, 155, 19);
		frame.getContentPane().add(odstranIngredientTextField);
		
		JButton odstranIngredient = new JButton("Odstráň ingredient!");
		odstranIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = odstranIngredientTextField.getText();
				if (text.isEmpty()) {
					return;
				}
				removeIngredient(text);
				odstranIngredientTextField.setText(null);
			}
		});
		odstranIngredient.setBounds(26, 337, 155, 21);
		frame.getContentPane().add(odstranIngredient);
		
		JButton odstranVsetkyIngredienty = new JButton("<html>Odstráň VŠETKY<br/> ingredienty</html>");
		odstranVsetkyIngredienty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAllIngredients();
			}
		});
		odstranVsetkyIngredienty.setBounds(26, 381, 155, 34);
		frame.getContentPane().add(odstranVsetkyIngredienty);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(210, 70, 153, 161);
		frame.getContentPane().add(scrollPane_1);
		
		verticalBoxRecepts = Box.createVerticalBox();
		scrollPane_1.setViewportView(verticalBoxRecepts);
		verticalBoxRecepts.setBorder(null);
		
		pridajReceptTextField = new JTextField();
		pridajReceptTextField.setColumns(10);
		pridajReceptTextField.setBounds(210, 241, 155, 19);
		frame.getContentPane().add(pridajReceptTextField);
		
		JButton pridajRecept = new JButton("Pridaj recept!");
		pridajRecept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = pridajReceptTextField.getText();
				if (text.isEmpty()) {
				    return;
				}

				String[] parts = text.split("-");

				String part1 = parts[0].trim();
				String part2 = parts[1].trim();

				String[] stringArray = part2.split(",");
				vytvorJedlo(part1, stringArray);
			}
		});
		pridajRecept.setBounds(210, 262, 155, 21);
		frame.getContentPane().add(pridajRecept);
		
		odstranReceptTextField = new JTextField();
		odstranReceptTextField.setColumns(10);
		odstranReceptTextField.setBounds(210, 317, 155, 19);
		frame.getContentPane().add(odstranReceptTextField);
		
		JButton odstranRecept = new JButton("Odstráň recept!");
		odstranRecept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = odstranReceptTextField.getText();
				if (text.isEmpty()) {
				    return;
				}
				removeRecept(text);
			}
		});
		odstranRecept.setBounds(210, 337, 155, 21);
		frame.getContentPane().add(odstranRecept);
		
		JButton odstranVsetkyRecepty = new JButton("<html>Odstráň VŠETKY<br/> recepty</html>");
		odstranVsetkyRecepty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAllRecepts();
			}
		});
		odstranVsetkyRecepty.setBounds(210, 381, 155, 34);
		frame.getContentPane().add(odstranVsetkyRecepty);
		
		addInitialValues();
	}
}
