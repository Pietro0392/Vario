/****************************************************************************
  NOME FILE   : uart.h 
  DESCRIZIONE : Header file driver UART
****************************************************************************/

/****************************************************************************
  DICHIARAZIONE COSTANTI
****************************************************************************/
#define FOSC			8000		// Frequenza clock [kHz]
#define	BAUDRATE		38400UL
#define	FIFO_SIZE		128

/****************************************************************************
  DICHIARAZIONE MACRO
****************************************************************************/

/****************************************************************************
  DICHIARAZIONE TIPI
****************************************************************************/
typedef struct _fifo {
	U8	idx_w;
	U8	idx_r;
	U8	count;
	U8 buff[FIFO_SIZE];
} FIFO;

/****************************************************************************
  PROTOTIPI FUNZIONI
****************************************************************************/
void uart_init(void);				// Inizializza UART
U8 uart_test(void);					// Controlla num. di byte nel buffer
U8 uart_get (void);					// Legge un byte
void uart_put (U8 data);			// Invia un byte
void uart_puts(U8 *str);			// Invia una stringa
void uart_puts_P(const U8 *str);	// Invia una stringa in FLASH
U16 uart_read(U8 *buf, U16 size);	// Preleva tutti i byte nel buffer
void uart_write(U8 *buf, U16 cnt);	// Invia il numero specificato di byte

