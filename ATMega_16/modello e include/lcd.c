/****************************************************************************
  NOME FILE   : lcd.c  
  DESCRIZIONE : Driver display LCD
  Ultima modifica: 11-10-2021
****************************************************************************/
#include <string.h>
#include "common.h"
#include "lcd.h"

// Prototipi funz. private
static void LCD_strobe(void);			// Genera impulso di strobe
static void LCD_8write(U8 c);			// Scrive su un byte su interf. 4 bit
static void LCD_4write(U8 c);			// Scrive su un byte su interf. 8 bit


/****************************************************************************
  FUNZIONE    : LCD_strobe
  DESCRIZIONE : Genera un impulso di strobe per il caricamento del dato nel
				display. Durata minima dell'impulso 500ns.
  PARAMETRI   : 
  RETURN CODE :  
****************************************************************************/
static void LCD_strobe(void)
{
	SET(LCD_CTRL, LCD_E);				// Alza linea E
	delayUs(1);							// Mantiene per 1us
	CLR(LCD_CTRL, LCD_E);				// Abbassa linea E
}

/****************************************************************************
  FUNZIONE    : LCD_8write
  DESCRIZIONE : Scrive un byte sul display LCD su interfaccia a 8 bit.
  PARAMETRI   : data - dato da trasmettere al display
  RETURN CODE :  
****************************************************************************/
static void LCD_8write(U8 data)
{
	U8 tmp;

	tmp = LCD_DATA & 0x0F;
	LCD_DATA = tmp | (data & 0xF0);		// Prepara il dato
	LCD_strobe();						// Carica il dato
}

/****************************************************************************
  FUNZIONE    : LCD_4write
  DESCRIZIONE : Scrive un byte sul display LCD su interfaccia a 4 bit
				(due nibble consecutivi).
  PARAMETRI   : data - dato da trasmettere al display
  RETURN CODE :  
****************************************************************************/
static void LCD_4write(U8 data)
{
	U8 tmp;

	tmp = LCD_DATA & 0x0F;
	LCD_DATA = tmp | (data & 0xF0);		// Prepara nibble alto
	LCD_strobe();						// Carica il dato

	tmp = LCD_DATA & 0x0F;
	LCD_DATA = tmp | (data<<4);			// Prepara nibble basso
	LCD_strobe();						// Carica il dato
}

/****************************************************************************
  FUNZIONE    : LCD_init
  DESCRIZIONE : Inizializza il display LCD secondo datasheet Hitachi HD44780U.
  PARAMETRI   : 
  RETURN CODE :  
****************************************************************************/
void LCD_init(void)
{
	delayMs(20);						// Attesa di 20ms dal power-on
	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	LCD_8write(LCD_CMD_8BIT);			// Inizia sequenza start-up
	delayMs(5);							// Attesa minima 4.1ms
	LCD_8write(LCD_CMD_8BIT);			// Ripete Attention 2a volta
	delayUs(200);						// Attesa minima 100us
	LCD_8write(LCD_CMD_8BIT);			// Ripete Attention 3a volta
	delayUs(50);						// Attesa minima 39us
	LCD_8write(LCD_CMD_4BIT);			// Imposta interfaccia a 4bit
	delayUs(50);						// Attesa minima 39us
	LCD_4write(LCD_CMD_FSET);			// Imposta duty 1/16, font 5x8 dot
	delayUs(50);						// Attesa minima 39us
	LCD_4write(LCD_CMD_DOFF);			// Display off
	delayUs(50);						// Attesa minima 39us
	LCD_4write(LCD_CMD_DON);			// Display on, cursor off, blink off
	delayUs(50);						// Attesa minima 39us
	LCD_4write(LCD_CMD_EMS);			// Autoincremento pos. cursore
	delayUs(50);						// Attesa minima 39us
}

/****************************************************************************
  FUNZIONE    : LCD_clear
  DESCRIZIONE : Cancella il display e pone il cursore nella posizione home.
  PARAMETRI   : 
  RETURN CODE : 
****************************************************************************/
void LCD_clear(void)
{
	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	LCD_4write(LCD_CMD_CLEAR);			// Comando Clear display
	delayMs(2);							// Attesa minima 1.53ms
}

/****************************************************************************
  FUNZIONE    : LCD_home
  DESCRIZIONE : Pone il cursore nella posizione home senza cancellare il
				display. 
  PARAMETRI   : 
  RETURN CODE :  
****************************************************************************/
void LCD_home(void)
{
	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	LCD_4write(LCD_CMD_HOME);			// Comando Return Home
	delayMs(2);							// Attesa minima 1.53ms
}

/****************************************************************************
  FUNZIONE    : LCD_cursor
  DESCRIZIONE : Rende visibile/invisibile il cursore.
  PARAMETRI   : stato - 0 off, 1 on
  RETURN CODE : 
****************************************************************************/
void LCD_cursor(U8 stato)
{
	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	if (stato)
		LCD_4write(LCD_CMD_CON);		// Accende il cursore
	else
		LCD_4write(LCD_CMD_COFF);		// Spegne il cursore
	delayUs(50);						// Attesa minima 39us
}

/****************************************************************************
  FUNZIONE    : LCD_shift
  DESCRIZIONE : Sposta il cursore di una posizione a destra/sinistra.
  PARAMETRI   : dir - 0 sinistra, 1 destra
  RETURN CODE : 
****************************************************************************/
void LCD_shift(U8 dir)
{
	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	if (dir)
		LCD_4write(LCD_CMD_CRIGHT);		// Sposta a destra cursore	
	else
		LCD_4write(LCD_CMD_CLEFT);		// Sposta a sinistra cursore
	delayUs(50);						// Attesa minima 39us
}

/****************************************************************************
  FUNZIONE    : LCD_goto
  DESCRIZIONE : Posiziona il cursore alla posizione specificata.
  PARAMETRI   : row - riga (0-3)
				col - colonna (0-19)
  RETURN CODE : 
****************************************************************************/
void LCD_goto(U8 row, U8 col)
{
	U8 addr;

	CLR(LCD_CTRL, LCD_RS);				// Seleziona control reg.
	switch(row & LCD_CMD_ADDR)			// Calcola indirizzo DDRAM
	{
		case 0:  addr = LCD_ADDR_LINE0 + col; break;
		case 1:  addr = LCD_ADDR_LINE1 + col; break;
		case 2:  addr = LCD_ADDR_LINE2 + col; break;
		case 3:  addr = LCD_ADDR_LINE3 + col; break;
		default: addr = LCD_ADDR_LINE0 + col; break;
	}
	LCD_4write(0x80 | addr);			// Imposta indirizzo DDRAM
	delayUs(50);						// Attesa minima 39us
}

/****************************************************************************
  FUNZIONE    : LCD_backsp
  DESCRIZIONE : Cancella il carattere nella posizione a sinistra del cursore.
  PARAMETRI   : 
  RETURN CODE :  
****************************************************************************/
void LCD_backsp(void)
{
	LCD_shift(0);						// Sposta cursore a sinistra
	LCD_putch(' ');						// Scrive spazio bianco
	LCD_shift(0);						// Sposta cursore a sinistra
}

/****************************************************************************
  FUNZIONE    : LCD_putch
  DESCRIZIONE : Visualizza un carattere alla posizione corrente del cursore.
  PARAMETRI   : c - carattere da visualizzare
  RETURN CODE : 
****************************************************************************/
void LCD_putch(U8 c)
{
	SET(LCD_CTRL, LCD_RS);				// Seleziona data reg.
	LCD_4write(c);						// Scrive il car. nella DDRAM
	delayUs(50);						// Attesa minima 43us
}

/****************************************************************************
  FUNZIONE    : LCD_puts
  DESCRIZIONE : Visualizza una stringa a partire dalla posizione corrente del
				cursore.
  PARAMETRI   : s - punt. stringa da visualizzare
  RETURN CODE : 
****************************************************************************/
void LCD_puts(char *s)
{
	SET(LCD_CTRL, LCD_RS);				// Seleziona data reg.
	while(*s)
	{
		LCD_4write(*s++);				// Scrive un car. nella DDRAM
		delayUs(50);					// Attesa minima 43us
	}
}

/****************************************************************************
  FUNZIONE    : LCD_writeln
  DESCRIZIONE : Visualizza una stringa sulla riga specificata completando con
				spazi bianchi.
  PARAMETRI   : row - riga (0-3)
				s   - punt. stringa da visualizzare
  RETURN CODE : 
****************************************************************************/
void LCD_writeln(U8 row, char *s)
{
	U8 i;

	LCD_goto(row, 0);
	LCD_puts(s);
	if (N_LCD_COLS > strlen(s)) //bugfix
	{
		for (i=0; i<(N_LCD_COLS-strlen(s)); i++)
			LCD_putch(' ');		
	}
}

