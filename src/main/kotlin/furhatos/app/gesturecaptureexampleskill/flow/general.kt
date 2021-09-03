package furhatos.app.gesturecaptureexampleskill.flow

import furhatos.app.gesturecaptureexampleskill.GesturecaptureexampleskillSkill
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures
import furhatos.records.Record
import java.io.BufferedReader
import java.io.InputStreamReader

val testGesture = getResourceGesture("/hmmNo.json")

val Idle: State = state {

    init {

    }

    onEntry {
        arKitGesture(testGesture)
    }
    onButton("Do gesture", color = Color.Green, instant = true) {
        arKitGesture(testGesture)
    }
}

/** load a gesture in .json form from the resources folder
 *
 * @param filePath Path in resources folder to the gesture .json
 */
fun getResourceGesture(filePath: String): Gesture {
    val resource = GesturecaptureexampleskillSkill::class.java.getResourceAsStream(filePath)
    return if (resource != null) {
        Record.fromJSON(BufferedReader(InputStreamReader(resource)).readText()) as Gesture
    } else {
        println("Failed to get resource : $filePath")
        Gestures.Blink
    }
}

/** play the gesture but turn of micro expressions before and turn them on after.
 *
 * @param gesture Gesture to perform
 */
fun FlowControlRunner.arKitGesture(gesture: Gesture) {
    furhat.setDefaultMicroexpression(blinking = false, facialMovements = false, eyeMovements = false)
    furhat.gesture(gesture, priority = 10, async = false)
    furhat.setDefaultMicroexpression(blinking = true, facialMovements = true, eyeMovements = true)
}
