/****************************************************************************
  NOME FILE   : lcd.h  
  DESCRIZIONE : Header file driver display LCD
****************************************************************************/

#define N_LCD_ROWS			2				// Numero righe display LCD
#define N_LCD_COLS			16				// Numero colonne display LCD

// Mappa I/O
#define LCD_DATA			PORTA			// control port
#define LCD_CTRL			PORTD			// data port
#define LCD_RS				PD4				// register select
#define LCD_E				PD6				// enable

// Mappa DDRAM
#define LCD_ADDR_LINE0		0x00			// linea 0
#define LCD_ADDR_LINE1		0x40			// linea 1
#define LCD_ADDR_LINE2		0x14			// linea 2
#define LCD_ADDR_LINE3		0x54			// linea 3

// Comandi LCD
#define LCD_CMD_8BIT		0x30			// Function set: interf. 8bit
#define LCD_CMD_4BIT		0x20			// Function set: interf. 4bit
#define LCD_CMD_FSET		0x28			// Function set: duty 1/16, font 5x8
#define LCD_CMD_DOFF		0x08			// Display control: display off
#define LCD_CMD_DON			0x0C			// Display control: display on
#define LCD_CMD_EMS			0x06			// Entry Mode Set: inc. pos. cursore
#define LCD_CMD_CLEAR		0x01			// Clear Display
#define LCD_CMD_HOME		0x02			// Return Home
#define LCD_CMD_COFF		0x0C			// Display control: cursore off
#define LCD_CMD_CON			0x0F			// Display control: cursore on
#define LCD_CMD_ADDR		0x03			// Set DDRAM Address
#define LCD_CMD_CLEFT		0x10			// Cursor shift: sposta a sinistra
#define LCD_CMD_CRIGHT		0x14			// Cursor shift: sposta a destra

// Prototipi funzioni
void LCD_init(void);						// Inizializza display LCD
void LCD_clear(void);						// Cancella display LCD
void LCD_home(void);						// Riporta il cursore all'origine
void LCD_cursor(U8 stato);					// Mostra/nasconde il cursore
void LCD_shift(U8 dir);						// Sposta cursore a destra/sinistra
void LCD_goto(U8 row, U8 col);				// Posiziona il cursore
void LCD_backsp(void);						// Cancella car. precedente
void LCD_putch(U8 c);						// Visualizza un carattere
void LCD_puts(char *s);						// Visualizza una stringa
void LCD_writeln(U8 row, char *s);			// Scrive una riga


