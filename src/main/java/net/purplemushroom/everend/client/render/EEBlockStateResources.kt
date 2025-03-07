package net.purplemushroom.everend.client.render;

import ru.timeconqueror.timecore.api.client.resource.BlockStateResource
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation
import ru.timeconqueror.timecore.api.util.json.json
import ru.timeconqueror.timecore.api.util.json.x
import ru.timeconqueror.timecore.api.util.json.y
import ru.timeconqueror.timecore.api.util.json.z

object EEBlockStateResources {
    @JvmStatic
    fun halfState(modelLocationTop: BlockModelLocation, modelLocationBottom: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "half=lower" {
                    "model" set modelLocationTop.toString()
                }
                "half=upper" {
                    "model" set modelLocationBottom.toString()
                }
            }
        })
    }

    @JvmStatic
    fun oneVarAxisXYZState(location: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "axis=y" {
                    "model" set location
                }
                "axis=z" {
                    "model" set location
                    x = 90
                }
                "axis=x" {
                    "model" set location
                    x = 90
                    y = 90
                }
            }
        })
    }

    @JvmStatic
    fun oneVarFacingNESWState(location: BlockModelLocation): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "facing=east" {
                    "model" set location
                    y = 90
                }
                "facing=north" {
                    "model" set location
                }
                "facing=south" {
                    "model" set location
                    y = 180
                }
                "facing=west" {
                    "model" set location
                    z = 270
                }
            }
        })
    }

    @JvmStatic
    fun anchorState(
        charge0: BlockModelLocation,
        charge1: BlockModelLocation,
        charge2: BlockModelLocation,
        charge3: BlockModelLocation,
        charge4: BlockModelLocation
    ): BlockStateResource {
        return BlockStateResource.fromJson(json {
            "variants" {
                "charges=0" {
                    "model" set charge0
                }
                "charges=1" {
                    "model" set charge1
                }
                "charges=2" {
                    "model" set charge2
                }
                "charges=3" {
                    "model" set charge3
                }
                "charges=4" {
                    "model" set charge4
                }
            }
        })
    }
}
