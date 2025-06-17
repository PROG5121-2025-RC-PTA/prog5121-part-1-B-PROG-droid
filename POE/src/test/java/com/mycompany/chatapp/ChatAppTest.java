/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class ChatAppTest {
    
    public ChatAppTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of isValidPhoneNumber method, of class ChatApp.
     */
    @Test
    public void testIsValidPhoneNumber() {
        System.out.println("isValidPhoneNumber");
        String phone = "";
        boolean expResult = false;
        boolean result = ChatApp.isValidPhoneNumber(phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidPassword method, of class ChatApp.
     */
    @Test
    public void testIsValidPassword() {
        System.out.println("isValidPassword");
        String password = "";
        boolean expResult = false;
        boolean result = ChatApp.isValidPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveUserToMemory method, of class ChatApp.
     */
    @Test
    public void testSaveUserToMemory() {
        System.out.println("saveUserToMemory");
        String name = "";
        String surname = "";
        String idNumber = "";
        String phone = "";
        String username = "";
        String password = "";
        boolean expResult = false;
        boolean result = ChatApp.saveUserToMemory(name, surname, idNumber, phone, username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showRegistrationForm method, of class ChatApp.
     */
    @Test
    public void testShowRegistrationForm() {
        System.out.println("showRegistrationForm");
        ChatApp.showRegistrationForm();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ChatApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ChatApp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
