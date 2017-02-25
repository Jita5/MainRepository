package core;
/*================================
 Author     : Jasthi.java Program
 Class Name : BigWord
 Date       :       
 Course     : ICS141 Programming with Objects (Siva Jasthi)
 Purpose    : Lab/Program Number
=================================*/

/*=====  BigWord ===========================
 * This java class is logical representation of a Word that comprises of 7 fields
 * 
 */

public class BigWord
{    

    //for representing the unique identifier of Big Word
    private String ID;    
    
  //for representing the topic
    private String topic;    

    //for representing the Telugu String
    private String telugu;    

    //for representing the English String
    private String english;    

    //for representing the clue for the Telugu word
    private String clue;    

    //for representing the resource location for the image file
    private String image;    

    //for representing the resource location for the sound file
    private String sound;

    /**
     * Default Constructor For BigWord Class
     */
     public BigWord()
     {
 
     };

    /**
    * Overloded Constructor For BigWord Class
    */
     public BigWord(String a_i_d, String a_topic, String a_telugu, String a_english, String a_clue, String a_image, String a_sound)
     { 
        ID = a_i_d;
        topic = a_topic;
        telugu = a_telugu;
        english = a_english;
        clue = a_clue;
        image = a_image;
        sound = a_sound;
     }

    /**
     * Set method for the variable ID
     */
    public void setID(String a__i_d)
    {
        ID = a__i_d;
    }

    /**
     * Set method for the variable telugu
     */
    public void setTelugu(String a_telugu)
    {
        telugu = a_telugu;
    }

    /**
     * Set method for the variable topic
     */
    public void setTopic(String a_topic)
    {
        topic = a_topic;
    }

    /**
     * Set method for the variable english
     */
    public void setEnglish(String a_english)
    {
        english = a_english;
    }

    /**
     * Set method for the variable clue
     */
    public void setClue(String a_clue)
    {
        clue = a_clue;
    }

    /**
     * Set method for the variable image
     */
    public void setImage(String a_image)
    {
        image = a_image;
    }

    /**
     * Set method for the variable sound
     */
    public void setSound(String a_sound)
    {
        sound = a_sound;
    }

    /**
     * Get method for the variable ID
     */
    public String getID( )
    {
        return ID;
    }

    /**
     * Get method for the variable telugu
     */
    public String getTelugu( )
    {
        return telugu;
    }

    /**
     * Get method for the variable topic
     */
    public String getTopic( )
    {
        return topic;
    }

    /**
     * Get method for the variable english
     */
    public String getEnglish( )
    {
        return english;
    }

    /**
     * Get method for the variable clue
     */
    public String getClue( )
    {
        return clue;
    }

    /**
     * Get method for the variable image
     */
    public String getImage( )
    {
        return image;
    }

    /**
     * Get method for the variable sound
     */
    public String getSound( )
    {
        return sound;
    }

    /** 
     * Returns the String representation of BigWord object 
     */
     public String toString()
    {
         String temp = 
            "\nID = " + ID +
            "\ntopic = " + topic +
             "\ntelugu = " + telugu +
            "\nenglish = " + english +
            "\nclue = " + clue +
            "\nimage = " + image +
            "\nsound = " + sound;

         return temp;
    }
     
     /** 
      * Returns whether Big Word has image or not 
      */
     public boolean hasImage()
     {
    	 return getImage()!=null;
     }


     /** 
      * Returns whether Big Word has sound or not 
      */
     public boolean hasSound()
     {
    	 return getSound()!=null;
     }
     
     /** 
      * Returns whether Big Word has sound or not 
      */
     public boolean hasClue()
     {
    	 return getClue()!=null;
     }

    /**
    * main( ) method for  BigWord Class
    */
    public static void main(String args[])
    { 
        BigWord bigword_1 = new BigWord( );
        bigword_1.setID("ID_dummy_string");
        bigword_1.setTopic("topic_dummy_string");
        bigword_1.setTelugu("telugu_dummy_string");
        bigword_1.setEnglish("english_dummy_string");
        bigword_1.setClue("clue_dummy_string");
        bigword_1.setImage("image_dummy_string");
        bigword_1.setSound("sound_dummy_string");
        System.out.println(bigword_1);
    }
}