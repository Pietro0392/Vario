/****************************************************************************
  NOME FILE   : common.h  
  DESCRIZIONE : Header file definizioni comuni
****************************************************************************/
#include <avr/io.h>
#include <avr/wdt.h>
#include <avr/interrupt.h>

// Valori booleani
#define TRUE		1
#define FALSE		0

// Macro manipolazione bit
#define BIT(x)		(1 << (x))
#define TST(x,y) 	(x & (1 << (y)))
#define SET(x,y)	(x |= (1 << (y)))
#define CLR(x,y) 	(x &= ~(1 << (y)))
#define TGL(x,y) 	(x ^= (1 << (y)))

// Istruzioni assembly
#define WDR()		asm volatile ("wdr")
#define NOP()		asm volatile ("nop")
#define SEI()		asm volatile ("sei")
#define CLI()		asm volatile ("cli")

// Tipi generici
typedef unsigned char       U8 ;
typedef unsigned short      U16;
typedef unsigned long       U32;
typedef signed char         S8 ;
typedef signed short        S16;
typedef long                S32;

// Prototipi funzioni
void delayUs(U16 us);
void delayMs(U16 ms);


