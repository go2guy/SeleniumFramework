package com.pages.tables;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TablePages {

	public TablePages(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	// Declare the Element to Table Rows

	@FindAll({ @FindBy(xpath = " //*[@id='customers']/tbody/tr") })
	public List<WebElement> tableRows;

	// This method will print all the cells in the table.

	public void printTable() {

		// And iterate over them, getting the cells

		for (WebElement row : tableRows) {

			List<WebElement> header = row.findElements(By.tagName("th"));

			if (header.size() > 0) {
				for (int x = 0; x < header.size(); x++)
					System.out.print(header.get(x).getText() + " | ");

			}

			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Print the contents of each cell
			if (cells.size() > 0) {
				for (int x = 0; x < cells.size(); x++)
					System.out.print(cells.get(x).getText() + " | ");

			}

			System.out.println("");

		}

	}

	public void getTableHeaders()

	{

		// And iterate over them, getting the cells

		for (WebElement row : tableRows) {

			List<WebElement> header = row.findElements(By.tagName("th"));

			// Print the contents of each cell

			for (WebElement head : header) {

				System.out.println("Header = [" + head.getText() + "]");

			}

		}

	}

	public boolean verifyTableCells(List<WebElement> table, String what) {

		for (WebElement row : table) {

			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Print the contents of each cell

			for (WebElement cell : cells) {

				if (cell.getText().equals(what))

					return true;

			}

		}

		return false;
	}

	public String getTableCell(List<WebElement> table, int row, int cell) {

		List<WebElement> cells = table.get(row).findElements(By.tagName("td"));

		return cells.get(cell).getText();

	}

	public String getTableHeader(List<WebElement> table, int row, int cell) {

		List<WebElement> cells = table.get(row).findElements(By.tagName("th"));

		return cells.get(cell).getText();

	}

}
