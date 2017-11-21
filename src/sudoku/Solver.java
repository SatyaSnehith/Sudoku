package sudoku;

public class Solver {
	private int[][] sdk;
	public static int[][][] sun = new int[9][9][9];
	public static void main(String[] args) {
	}
	public Solver(int sdk[][]) {
		this.sdk = sdk;
		createSUN();
		//	if((new Check(sdk)).getResult()) {
			//	for(int i = 0; i < 9; ++i) {
			//	System.out.println(i);
			//	break;
                    //        }
                  //      }
		//	else {
				checkSquarePossibility();
				checkRAC();
				checkPossibility();
				checkSquare();
				
		//	}
		//for(int i = 0; i < 9; ++i)
		checkSquarePossibility();
		printIt();
		printSudoku();
		if((new Check(sdk)).getResult())
			System.out.println("True");
		else
			System.out.print("False");
	}
	public int[][] getResult() {
		return sdk;
	}
	public void printSudoku() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				System.out.print(sdk[i][j]);
				if((j + 1) % 3 == 0)
					System.out.print("|");
				else
					System.out.print(" ");
			}
			if((i + 1) % 3 == 0)
				System.out.print("\n------------------");
			System.out.print("\n");
		}
	}
	public void checkSquare() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				if(sdk[i][j] != 0) {
					removeSquare(i, j);
                                        checkSUN();
				}
			}
		}
	}
	public void removeSquare(int i, int j) {
		int x = (i / 3) * 3;
		int y = (j / 3) * 3;
		int n = sdk[i][j];
		// System.out.println(i + " " + j + " in " + x + " " + y);
		for(int a = x; a < x + 3; ++a) {
			for(int b = y; b < y + 3; ++b) {
				if((!(a == i && b == j)) && sdk[a][b] == 0) {
					remove(n, a, b);
					//System.out.println(sdk[i][j] + " removed from " + a + "" + b + " Because of " + i + " " + j);
				}
			}
		}
	}
	public void createSUN() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				for(int k = 0; k < 9; ++k) {
					if(sdk[i][j] == 0) {
						sun[i][j][k] = k + 1;
					}
					else {
						sun[i][j][k] = sdk[i][j];
					}
				}
			}
		}
	}
	public void checkSUN() {
		int csun = 0;
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				csun = checkSUN(i, j);
				if(csun != 0) {
					// System.out.println(checkSUN(i, j) + " from " + i + " " + j);
					set(csun, i, j);
				}
			}
		}
	}
	public int checkSUN(int i, int j) {
		int z = 0, n = 0 , a = 0;
		for(int k = 0; k < 9; ++k) {
			if(sun[i][j][k] == 0) {
				++z;
			} else {
				++n;
				a = k;
			}
		}
		if(z == 8 && n == 1) {
			return sun[i][j][a];
		}
		return 0;
	}
	public void checkPossibility() {            
		int h, v, temph, tempv;
		for(int n = 1; n < 10; ++n) {
			for(int i = 0; i < 9; ++i) {
				h = 0;
				//v = 0;
				temph = 0;
				//tempv = 0;
				for(int j = 0; j < 9; ++j) {
					if(checkIf(n, i, j)) {
                                                System.out.println(n  + " present in " + i + " " + j);
						++h;
						temph = j;
					}
//					if(checkIf(n, j, i)) {
//						++v;
//						tempv = j;
//					}
				}
				if(h == 1)
                                    set(n, i, temph);
//				if(v == 1)
//                                    set(n, tempv, i);
			}
		}
	}
//        public boolean checkRowPossibility(int n, int i) {
//            int x = 0;
//            for(int j = 0; j < 9; ++j) {
//                for(int k = 0; k < 9; ++k) {
//                    if(sun[i][j][k] == n)
//                        ++x;
//                }
//            }
//            if(x == 1)
//                return true;
//            return false;
//        }
	public void checkSquarePossibility() {
		for(int i = 0; i < 9; i += 3) {
			for(int j = 0 ; j < 9; j += 3) {
				checkSP(i, j);
			}
		}
	}
	public void checkSP(int x, int y) {
		int a, tempi, tempj;
		for(int n = 1; n < 10; ++n) {
			a = 0;
			tempi = 0;
			tempj = 0;
			for(int i = x; i < x + 3; ++i) {
				for(int j = y; j < y + 3; ++j) {
					if(checkIf(n, i, j)) {
                                                //System.out.println(n + " present in " + i + " " + j);
						++a;
						tempi = i;
						tempj = j;
					}
				}
			}
			if(a == 1) {
				set(n, tempi, tempj);
                                System.out.println(n + " is set to " + tempi + " " + tempj);
			}
		}
	}
	public void set(int n, int i, int j) {
		for(int k = 0; k < 9; ++k) {
			sun[i][j][k] = n;
		}
		sdk[i][j] = n;
	}
	public boolean checkIf(int n, int i, int j) {
            if(sdk[i][j] == 0) {
        //        for(int k = 0; k < 9; ++k) {
                    if(sun[i][j][n - 1] == n)
                        return true;
                    else
                        return false;
        //        }
            }
                return false;
	}
	public void printIt() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				for(int k = 0; k < 9; ++k) {
					System.out.print(sun[i][j][k]);
				}
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	public void checkRAC() {
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 9; ++j) {
				if(sdk[i][j] != 0) {
					rac(i, j);
					// checkSquare(i, j);
				}
			}
		}
	}
	public void rac(int i, int j) {
		int n = sdk[i][j];
		// System.out.println("(" + n + ")" + " From " + i + " " + j);
		for(int x = 0; x < 9; ++x) {
			if(x != j)
				remove(n, i, x);
			if(x != i)
				remove(n, x, j);
		}
                checkSUN();
	}
	public void remove(int n, int i, int j) {
		for(int k = 0; k < 9; ++k) {
			if(sun[i][j][k] == n) {
				sun[i][j][k] = 0;
			}
		}
	}
}
