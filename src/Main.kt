/**
 * ===============================================================
 * Kotlin GUI Basic Starter
 * ===============================================================
 *
 * This is a starter project for a simple Kotlin GUI application.
 * The Java Swing library is used, plus the FlatLAF look-and-feel
 * for a reasonably modern look.
 */

import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    MainWindow()            // Create and show the UI
}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow : JFrame(), ActionListener {

    // Fields to hold the UI elements

    private lateinit var redJTextArea: JTextField
    private lateinit var blueJTextArea: JTextField
    private lateinit var greenJTextArea: JTextField

    private lateinit var redUpButton: JButton
    private lateinit var blueUpButton: JButton
    private lateinit var greenUpButton: JButton

    private lateinit var redDownButton: JButton
    private lateinit var blueDownButton: JButton
    private lateinit var greenDownButton: JButton

    private lateinit var colourField: JLabel
    private lateinit var hexOut: JTextField




    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Gui RGB to HEX code"
        contentPane.preferredSize = Dimension(500, 350)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val defaultFont = Font(Font.SANS_SERIF, Font.PLAIN, 30)



        redJTextArea = JTextField("0")
        redJTextArea.bounds = Rectangle(60, 40, 100,70)
        redJTextArea.font = defaultFont
        redJTextArea.addActionListener(this)
        add(redJTextArea)

        blueJTextArea = JTextField("0")
        blueJTextArea.bounds = Rectangle(60, 240, 100,70)
        blueJTextArea.font = defaultFont
        blueJTextArea.addActionListener(this)
        add(blueJTextArea)

        greenJTextArea = JTextField("0")
        greenJTextArea.bounds = Rectangle(60, 140, 100,70)
        greenJTextArea.font = defaultFont
        greenJTextArea.addActionListener(this)
        add(greenJTextArea)



        redUpButton = JButton("+")
        redUpButton.bounds = Rectangle(180, 40, 30, 30)
        redUpButton.font = font
        redUpButton.foreground = Color.DARK_GRAY
        redUpButton.addActionListener(this)     // Handle any clicks
        add(redUpButton)

        redDownButton = JButton("-")
        redDownButton.bounds = Rectangle(180, 80, 30, 30)
        redDownButton.font = font
        redDownButton.foreground = Color.DARK_GRAY
        redDownButton.addActionListener(this)     // Handle any clicks
        add(redDownButton)



        blueUpButton = JButton("+")
        blueUpButton.bounds = Rectangle(180, 240, 30, 30)
        blueUpButton.font = font
        blueUpButton.foreground = Color.DARK_GRAY
        blueUpButton.addActionListener(this)     // Handle any clicks
        add(blueUpButton)

        blueDownButton = JButton("-")
        blueDownButton.bounds = Rectangle(180, 280, 30, 30)
        blueDownButton.font = font
        blueDownButton.foreground = Color.DARK_GRAY
        blueDownButton.addActionListener(this)     // Handle any clicks
        add(blueDownButton)


        greenUpButton = JButton("+")
        greenUpButton.bounds = Rectangle(180, 140, 30, 30)
        greenUpButton.font = font
        greenUpButton.foreground = Color.DARK_GRAY
        greenUpButton.addActionListener(this)     // Handle any clicks
        add(greenUpButton)

        greenDownButton = JButton("-")
        greenDownButton.bounds = Rectangle(180, 180, 30, 30)
        greenDownButton.font = font
        greenDownButton.foreground = Color.DARK_GRAY
        greenDownButton.addActionListener(this)     // Handle any clicks
        add(greenDownButton)




        colourField = JLabel("")
        colourField.bounds = Rectangle(250, 40, 200, 200)
        colourField.font = defaultFont
        colourField.isOpaque = true
        colourField.background = Color.black
        add(colourField)

        hexOut = JTextField("#000000")
        hexOut.bounds = Rectangle(250, 250, 200, 50)
     //   hexOut.horizontalAlignment = SwingConstants.CENTER
        hexOut.font = defaultFont
        hexOut.addActionListener(this)
        add(hexOut)
    }


    /**
     * Handle any UI events (e.g. button clicks)
     */
    override fun actionPerformed(e: ActionEvent?) {
        var redValue = redJTextArea.text.toIntOrNull() ?: 0
        var greenValue = greenJTextArea.text.toIntOrNull() ?: 0
        var blueValue = blueJTextArea.text.toIntOrNull() ?: 0

        when (e?.source) {
            redUpButton -> redValue ++
            redDownButton -> redValue --
            blueUpButton -> blueValue ++
            blueDownButton -> blueValue --
            greenUpButton -> greenValue ++
            greenDownButton -> greenValue --
            hexOut -> { // If the hex code is changed
                val hexCode = hexOut.text.trim()

                if (hexCode.startsWith("#") && hexCode.length == 7) {
                    try {
                        redValue = Integer.parseInt(hexCode.substring(1, 3), 16)
                        greenValue = Integer.parseInt(hexCode.substring(3, 5), 16)
                        blueValue = Integer.parseInt(hexCode.substring(5, 7), 16)
                    } catch (e: NumberFormatException) {
                        // Handle invalid hex code input
                        JOptionPane.showMessageDialog(this, "Invalid Hex Code", "Error", JOptionPane.ERROR_MESSAGE)
                        return
                    }
                }}
        }

        redValue = (redValue + 256) % 256
        greenValue = (greenValue + 256) % 256
        blueValue = (blueValue + 256) % 256

        redJTextArea.text = redValue.toString()
        greenJTextArea.text = greenValue.toString()
        blueJTextArea.text = blueValue.toString()

        updateUI()
    }
    private fun updateUI() {
        val redValue = redJTextArea.text.toInt()
        val greenValue = greenJTextArea.text.toInt()
        val blueValue = blueJTextArea.text.toInt()


        colourField.background = Color(redValue, greenValue, blueValue)

        val redHex = "%02X".format(redValue)
        val greenHex = "%02X".format(greenValue)
        val blueHex = "%02X".format(blueValue)

        hexOut.text = "#$redHex$greenHex$blueHex"
    }

}

