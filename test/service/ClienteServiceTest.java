/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import app.ClienteService;
import app.ComunicacaoTCP;
import app.FileMessage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 750370629
 */
public class ClienteServiceTest {
    
    public ClienteServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of conectar method, of class ClienteService.
     */
    @Test
    public void testConectar_0args() {
        System.out.println("conectar");
        ClienteService instance = new ClienteService();
        ComunicacaoTCP expResult = null;
        ComunicacaoTCP result = instance.conectar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conectar method, of class ClienteService.
     */
    @Test
    public void testConectar_String_int() {
        System.out.println("conectar");
        String ip = "";
        int porta = 0;
        ClienteService instance = new ClienteService();
        ComunicacaoTCP expResult = null;
        ComunicacaoTCP result = instance.conectar(ip, porta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviar method, of class ClienteService.
     */
    @Test
    public void testEnviar() throws Exception {
        System.out.println("enviar");
        FileMessage fm = null;
        ClienteService instance = new ClienteService();
        instance.enviar(fm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desconectar method, of class ClienteService.
     */
    @Test
    public void testDesconectar() throws Exception {
        System.out.println("desconectar");
        FileMessage fm = null;
        ClienteService instance = new ClienteService();
        boolean expResult = false;
        boolean result = instance.desconectar(fm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
