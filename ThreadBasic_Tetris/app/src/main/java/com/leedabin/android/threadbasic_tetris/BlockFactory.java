package com.leedabin.android.threadbasic_tetris;

import android.os.Handler;

import java.util.Random;

/**
 * Created by Dabin on 2016-10-18.
 */
public class BlockFactory {


    //모든 타입과 뱡향을 다 가진 블록 4차원 배열이다.
    static int  blocks[][][][] = {
            // Block I
            {

                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {1, 1, 1, 1}
                    },
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {1, 1, 1, 1}
                    }

            },
            // Block J
            {
                    {
                            {0, 0, 0, 0},
                            {0, 0, 2, 0},
                            {0, 0, 2, 0},
                            {0, 2, 2, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 2, 0, 0},
                            {0, 2, 2, 2}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 2, 2, 0},
                            {0, 2, 0, 0},
                            {0, 2, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {2, 2, 2, 0},
                            {0, 0, 2, 0}

                    }
            },
            // Block L
            {
                    {
                            {0, 0, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 3, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 3, 3, 3},
                            {0, 3, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 3, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 3, 0}

                    },
                    {

                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 3, 0},
                            {3, 3, 3, 0}
                    }
            },
            // Block S
            {
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 4, 4, 0},
                            {4, 4, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 4, 4, 0},
                            {4, 4, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0}
                    }
            },
            // Block Z
            {
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 5, 5, 0},
                            {0, 0, 5, 5}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 5, 5, 0},
                            {0, 0, 5, 5}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0}

                    }
            },
            // Block T
            {
                    {
                            {0, 0, 0, 0},
                            {0, 6, 0, 0},
                            {0, 6, 6, 0},
                            {0, 6, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {6, 6, 6, 0},
                            {0, 6, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 6, 0, 0},
                            {6, 6, 0, 0},
                            {0, 6, 0, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 6, 0, 0},
                            {6, 6, 6, 0},

                    }
            },
            // Block O
            {
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0}

                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0}
                    }
            },
    };

    //블럭을 생성하는 Factory Method
    public static Block newBlcok(Handler handler) {
        Random random = new Random();
        int blockIndex = random.nextInt(7);
        Block block = new Block(MainStage.interval,blocks[blockIndex],handler);
        block.setDaemon(true);
        return block;
    }
}
