/*********************************************
  NOME FILE   : ___.c
  DESCRIZIONE : 
  MCU	      : Atmel ATmega16 @ 8MHz
  COMPILATORE : WinAVR-20070525 (GCC 4.1.2)
  AUTORE      : 
  VERSIONE    : 1.0
*********************************************/
#include "common.h"
#include "lcd.h"
#include "timers.h"
#include <stdio.h>

/*********************************************
   FUNZIONE   : mcu_setup
   DESCRIZIONE: Inizializzazione periferiche MCU.
   PARAMETRI  : nessuno
   RETURN CODE: nessuno
*********************************************/
void mcu_setup(void)
{
	// Configurazione porte
	DDRA  = 0b11110000;	
	PORTA = 0b00001111;
	DDRB  = 0b00000000;
	PORTB = 0b11111111;
	DDRC  = 0b01010100;
	PORTC = 0b10101011;
	DDRD  = 0b01010000;
	PORTD = 0b10101111;
	
	/*
	Pc2,Pc4,Pc6 sono i 3 leds
	*/
	
	LCD_init();
	LCD_clear();
	LCD_home();
	LCD_cursor(1);
	
	TCCR0=0b00001011;
	OCR0=124;
	SET(TIMSK, OCIE0);
	
	//configurazione display
	

	// Configurazione periferiche utilizzate
	


	// watchdog
	wdt_reset();                   
	wdt_disable();        //disabilita wdt

	// interrupt
	SEI();                //abilita interruzioni
}


/*********************************************
   FUNZIONE   : main
   DESCRIZIONE: Main loop
   PARAMETRI  : nessuno
   RETURN CODE: ignorato
*********************************************/

volatile int I;
volatile int B;
char secondi[17];

ISR(TIMER0_COMP_vect)		// interrupt ogni millisecondo
{
	update_timers();
}

int main(void)
{
	mcu_setup();
	
	while(1) 
	{
		if(B==0)
		{
					set_timer(1,150);
					set_timer(2,400);
					set_timer(3,1000);
					B=1;
		}
		
		if(timeout(1))
		{
				TGL(PORTC,6);
				set_timer(1,150);
		}
			
		if(timeout(2))
		{
				TGL(PORTC,4);
				set_timer(2,400);
		}
			
		if(timeout(3))
		{
				TGL(PORTC,2);
				set_timer(3,1000);
				I++;
				sprintf(secondi, "Secondi: %02d",I);
				LCD_writeln(0,secondi);
		}
		
	}
}


