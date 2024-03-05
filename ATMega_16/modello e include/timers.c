/****************************************************************************
  NOME FILE   : timers.c 
  DESCRIZIONE : Timers software
****************************************************************************/
#include "common.h"
#include "timers.h"

/****************************************************************************
   DICHIARAZIONE VARIABILI
****************************************************************************/
U16 timer[N_TIMERS];		   // Contatori timer

/****************************************************************************
   FUNZIONE   : update_timers
   DESCRIZIONE: Aggiornamento dei timers. Deve essere richiamata ad intervalli
		regolari, all'interno di un interrupt temporizzato, che definirà
		il valore del tick.
   PARAMETRI  : 
   RETURN CODE: 
****************************************************************************/
void update_timers(void)
{
	U8 i; 
	for(i=0; i<N_TIMERS; i++)
		if (timer[i]) 
			timer[i]--;
}

/****************************************************************************
   FUNZIONE   : set_timer
   DESCRIZIONE: Imposta un timer
   PARAMETRI  : n - identificatore del timer
                val - numero di tick
   RETURN CODE: 
****************************************************************************/
void set_timer(U8 n, U16 val)
{
	CLI();						// Disabilita interruzioni
	timer[n] = val;				// Imposta timer
	SEI();						// Riabilita interruzioni
}

/****************************************************************************
   FUNZIONE   : timeout
   DESCRIZIONE: Verifica se un timer è scaduto
   PARAMETRI  : n - identificatore del timer
   RETURN CODE: TRUE - timer scaduto
   				FALSE- timer non scaduto
****************************************************************************/
U8 timeout(U8 n)
{
    U8 ret = FALSE;

	CLI();						// Disabilita interruzioni
	if (!timer[n]) 
		ret = TRUE;	// Se timer scaduto restituisce TRUE
	SEI();						// Riabilita interruzioni
    return ret;
}

/****************************************************************************
   FUNZIONE   : delay
   DESCRIZIONE: Aspetta un numero di tick
   PARAMETRI  : val - numero di tick
   RETURN CODE: 
****************************************************************************/
void delay(U16 val)
{
	set_timer(TIM_DELAY, val);
	while(!timeout(TIM_DELAY))
	{
	}
}
