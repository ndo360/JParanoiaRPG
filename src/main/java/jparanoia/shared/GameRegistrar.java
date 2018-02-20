package jparanoia.shared;

public class GameRegistrar {
    /*  11 */   public static String formattedDesc = "";

    public static JPGameInfo[] getGames() {
        return null;
//        /*  15 */
//        if ( !urlsLoaded( true ) ) {
//            return null;
//        }
//        /*  20 */
//        ArrayList localArrayList = new ArrayList( 10 );
//        try {
//            /*  25 */
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=getgames" );
//            String str;
//
//            /*  29 */
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//
//
//                /*  33 */
//                StringTokenizer localStringTokenizer = new StringTokenizer( str, "\t" );
//
//                /*  35 */
//                if ( localStringTokenizer.countTokens() == 2 ) {
//                    /*  37 */
//                    localArrayList.add( new JPGameInfo( localStringTokenizer.nextToken(), localStringTokenizer.nextToken() ) );
//                }
//            }
//
//            /*  41 */
//            localBufferedReader.close();
//        } catch ( IOException localIOException ) {
//            /*  45 */
//            localIOException.printStackTrace();
//            /*  46 */
//            JParanoia.errorMessage( "Network Error", "Unable to get games from game registry:\nRegistry inaccessible. Check your network connection.\n(JParanoia server may be down.)" );
//
//            /*  51 */
//            return null;
//        }
//        /*  54 */
//        JPGameInfo[] arrayOfJPGameInfo = (JPGameInfo[]) localArrayList.toArray( new JPGameInfo[localArrayList.size()] );
//
//        /*  56 */
//        return arrayOfJPGameInfo;
    }

    private static boolean urlsLoaded( boolean paramBoolean ) {
        return false;
//        /* 196 */
//        if ( JPURLs.gameRegistrarURL == null ) {
//            try {
//                /* 198 */
//                JPURLs.getURLs();
//            } catch ( IOException localIOException ) {
//                /* 200 */
//                if ( paramBoolean ) {
//                    /* 202 */
//                    localIOException.printStackTrace();
//                    /* 203 */
//                    JParanoia.errorMessage( "Network Error", "Unable to acquire game registry URL:\nServer inaccessible. Check your network connection." );
//                }
//
//
//
//                /* 208 */
//                return false;
//            }
//        }
//
//        /* 212 */
//        return true;
    }

    public static void addGame( String paramString ) {
        System.out.println("Not adding game to the list as paranoia-live is obviously dead");
//        return;
//
//        /*  61 */
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//        try {
//            /*  66 */
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=" +
//                    paramString );
//            /*  71 */
//            int i = -1;
//            String str;
//            /*  73 */
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//            /*  80 */
//            localBufferedReader.close();
//            /*  84 */
//            jparanoia.server.JPServer.gameRegistered = true;
//        } catch ( IOException localIOException ) {
//            /*  88 */
//            localIOException.printStackTrace();
//            /*  89 */
//            JParanoia.errorMessage( "Network Error", "Unable to add game to game registry:\nRegistry inaccessible. Check your network connection." );
//            /*  93 */
//            return;
//        }
    }

    public static void removeGame() {
//        /*  99 */
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//        try {
//            /* 103 */
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "erase=1" );
//            String str;
//
//            /* 107 */
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//            /* 113 */
//            localBufferedReader.close();
//
//            /* 115 */
//            jparanoia.server.JPServer.gameRegistered = false;
//        } catch ( IOException localIOException ) {
//            /* 118 */
//            localIOException.printStackTrace();
//            /* 119 */
//            JParanoia.errorMessage( "Network Error", "Unable to erase game from game registry:\nRegistry inaccessible. Check your network connection." );
//        }
    }

    public static void deleteUnreachableGame( String paramString ) {
//        /* 129 */
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//        /* 131 */
//        System.out.println( "Attempting to remove unreachable game " + paramString + " ..." );
//        try {
//            /* 135 */
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=JP-REMOVE:" +
//                    paramString );
//            String str;
//
//            /* 139 */
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//            /* 145 */
//            localBufferedReader.close();
//        } catch ( IOException localIOException ) {
//            /* 149 */
//            localIOException.printStackTrace();
//            /* 150 */
//            JParanoia.errorMessage( "Network Error", "Unable to remove unreachable game from game registry:\nRegistry inaccessible. Check your network connection." );
//            /* 154 */
//        }
    }

    public static String getIP() {
        //FIXME: get public IPs modern way
        return "go figure!";
//        /* 160 */
//        if ( !urlsLoaded( false ) ) {
//            return "fail";
//        }
//        try {
//            /* 165 */
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=getip" );
//
//
//            /* 168 */
//            String str2 = null;
//            String str1;
//            /* 170 */
//            while ( ( str1 = localBufferedReader.readLine() ) != null ) {
//
//
//                /* 174 */
//                if (str1.contains("JP-REMOTE-IP")) {
//                    /* 175 */
//                    str2 = str1.substring( str1.indexOf( ":" ) + 1 );
//                }
//            }
//
//
//            /* 180 */
//            localBufferedReader.close();
//            /* 181 */
//            return str2;
//        } catch ( IOException localIOException ) {
//        }
//        /* 190 */
//        return "fail";
    }
}
