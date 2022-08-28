package ambulancia;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * SAMUdoBruno - a robot by (your name here)
 */
public class SAMUdoBruno extends Robot
{
	/**
	 * run: SAMUdoBruno's default behavior
	 */
	
	double margem;
	boolean espreitador;
	double vida;
	
	public void run() 
    {
	    //ROBOT COLORS
		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.blue);
		setScanColor(Color.black);
		setBulletColor(Color.orange);

		//GET MARGEM DO CAMPO DE BATALHA
		margem = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		
		//ESPREITADOR
		espreitador = false;

		//CORRA PARA A MARGEM
		turnLeft(getHeading() % 90); //
		ahead(margem);
		
		//GIRA A ARMA 90 GRAUS PARA A DIREITA
		espreitador = true;
		turnGunRight(90);
		turnRight(90);
			
		// Robot main loop
		while(true)
        {
		    //ESPREITADOR ATIVADO
			espreitador = true;
			//CORRA PARA A MARGEM
			ahead(margem);
			//ESPREITADOR DESATIVADO
			espreitador = false;
			//VIRANDO PARA A CORRER PARA A PROXIMA PAREDE
			turnRight(90);
			//GUARDA ESTAMINA
			vida = getEnergy();
		}
	}

	//ENERGIA BAIXA == CORRER AO SER ATINGIDO
	public void onHitByBullet(HitByBulletEvent e)
	{
    	double bearing = e.getBearing(); //GUARDA A DIREÇÃO DO TIRO RECEBIDO
	    if(vida < 100)
		{ // FUGIR COMO UM COVARDE
	        turnRight(-bearing); //FOGE EM NA DIREÇÃO OPOSTA DO TIRO RECEBIDO
	        ahead(100);
			turnRight(45);
			turnGunRight(360); 
	    }
	}	
	
	//SE SCANEAR ALGUEM ATIRE 
	public void onScannedRobot(ScannedRobotEvent e)
    {
		fire(2);
		if(espreitador)
        {
		    //SCAN MANUAL PARA DISPARAR O EVENTO DE SCANNEDROBOT DENOVO
            scan(); 
		}
	}
	
	//CASO COLIDA COM OUTRO ROBO
	public void onHitRobot(HitRobotEvent e)
    {
		//SE O ALVO ESTIVER NA FRENTE DO SAMU CORRA PARA TRÁS
		if (e.getBearing() > -90 && e.getBearing() < 90)
        {
			back(100);
		} 
        else 
        {
        ahead(100);
		}
	}
	
	//SPAN EMOTE SE GANHAR
	public void onWin(WinEvent e)
    {
		for (int i = 0; i < 50; i++)
        {
			turnRight(40);
			turnLeft(20);
			turnRight(10);
			turnLeft(20);
			turnRight(40);
		}
	}	
}
