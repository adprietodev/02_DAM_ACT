package es.florida.examen_psp;

import java.util.ArrayList;
import java.util.List;

public class Subproceso {

	private String numPieces = "60";
	private List<Piece> pieceMaked = new ArrayList<Piece>();

	int numR = 0;

	public static void main(String[] args) {

		Subproceso sP = new Subproceso();
		String[] type = { "clavo", "tuerca", "tornillo" };

		if (args.length != 0 ) {
			if(!args[0].equals("0")) {
				sP.numPieces = args[0];
			}
			
		}

		Thread[] hPieces = new Thread[Integer.parseInt(sP.numPieces)];

		int limit = Integer.parseInt(sP.numPieces) / 3;

		int cantClavo = 0;
		int cantTuerca = 0;
		int cantTornillo = 0;

		//System.out.println(limit);

		for (int i = 0; i < hPieces.length; i++) {

			boolean checkLimit = false;
			String t = type[sP.randomNumber()];

			while (!checkLimit) {
				if ((t.equals("tornillo") && cantTornillo == limit) || (t.equals("tuerca") && cantTuerca == limit)
						|| (t.equals("clavo") && cantClavo == limit)) {
					t = type[sP.randomNumber()];
					// System.out.println(cantClavo+ " "+ cantTuerca+" "+cantTornillo);
				} else {
					checkLimit = true;
				}
			}

			Piece piece = new Piece(i + 1, t, sP);

			hPieces[i] = new Thread(piece);

			hPieces[i].start();

			if (t.equals("clavo"))
				cantClavo++;
			if (t.equals("tuerca"))
				cantTuerca++;
			if (t.equals("tornillo"))
				cantTornillo++;

		}

		for (Thread thread : hPieces) {
			try {
				thread.join(); // Esperar a que el hilo termine
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		for (Piece p : sP.pieceMaked) {
			System.out.println("Numero pieza: " + p.id + " - Tipo: " + p.type);
		}

	}

	public int randomNumber() {

		return (int) (Math.random() * 3);
	}

	public synchronized void fabricacion(Piece p) {
		pieceMaked.add(p);
	}

	public static class Piece implements Runnable {

		private int id;
		private String type;
		private Subproceso sP;

		public Piece(int id, String type, Subproceso sP) {
			this.id = id;
			this.type = type;
			this.sP = sP;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			sP.fabricacion(new Piece(id, type, sP));

		}

	}
}
