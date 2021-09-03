package furhatos.app.gesturecaptureexampleskill

import furhatos.app.gesturecaptureexampleskill.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class GesturecaptureexampleskillSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
