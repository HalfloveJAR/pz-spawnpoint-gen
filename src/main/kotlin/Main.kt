import java.io.FileWriter
import java.lang.Exception

/*
 * Author: Halflove
 * Title: pz-spawnpoint-gen
 * Description: Project Zomboid spawn points lua file generator
 */

fun main(args: Array<String>) {

    // Get user input for cell
    println("Enter cell (ex: 20x33)")
    var inputCell = readLine()!!.toString()
    println("Cell: $inputCell")

    // Get user input for rel
    println("Enter rel (ex: 74x142)")
    var inputRel = readLine()!!.toString()
    println("Rel: $inputRel")

    // User inputs desired name of spawn location
    println("Enter desired spawn point name")
    var inputName = readLine()!!.toString()
    println("Name: $inputName")

    // Call createSpawnPointsLua function
    createSpawnPointsLua(inputCell, inputRel, inputName)
}

internal fun createSpawnPointsLua(cell: String, rel: String, name: String) {

    // Split cell and rel input into separate ints
    val delimiter = "x"
    val cellParts = cell.split(delimiter)
    val relParts = rel.split(delimiter)

    var worldX: Int = cellParts[0].toInt()
    var worldY: Int = cellParts[1].toInt()
    var posX: Int = relParts[0].toInt()
    var posY: Int = relParts[1].toInt()

    // create lua file
    try {

        var spawnPointFile = FileWriter(name + "_spawnpoints.lua")
        spawnPointFile.write("function SpawnPoints()\n" +
            "    return {\n" +
            "        unemployed = {\n" +
            "            { worldX = $worldX, worldY = $worldY, posX = $posX, posY = $posY }\n" +
            "        }\n" +
            "    }\n" +
            "end")
        spawnPointFile.close()

        createSpawnRegionsLua(name)

    } catch(ex:Exception) {
        print(ex.message)
    }

}

internal fun createSpawnRegionsLua(name: String){

    // remove spaces
    val fileName: String = name

    // create lua file
    try {

        var spawnRegionFile = FileWriter("world_spawnregions.lua")
        spawnRegionFile.write("function SpawnRegions()\n" +
                "    return {\n" +
                "        { name = \"$name\", serverfile = \"$fileName\" }\n" +
                "    }\n" +
                "end")
        spawnRegionFile.close()

    } catch(ex:Exception) {
        print(ex.message)
    }
}