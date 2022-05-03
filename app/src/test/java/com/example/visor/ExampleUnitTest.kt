package com.example.visor

import com.example.visor.models.Moneda
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ExampleUnitTest : DescribeSpec({
    describe("Moneda") {
        it("estaEnAlza con variación positiva devuelve true") {
            Moneda(variacionEn24hs = 2.0).estaEnAlza() shouldBe true
        }

        // TODO: Agregar tests acá
    }
})