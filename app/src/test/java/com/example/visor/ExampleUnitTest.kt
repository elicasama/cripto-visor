package com.example.visor

import com.example.visor.models.Moneda
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ExampleUnitTest : DescribeSpec({
    describe("Moneda") {
        it("estaEnAlza con variación positiva devuelve true") {
            Moneda(variacionEn24hs = 2.0).estaEnAlza() shouldBe true
        }

        it("estaEnAlza con variación negariva devuelve false") {
            Moneda(variacionEn24hs = -2.0).estaEnAlza() shouldBe false
        }

        it("variacionFormateada devuelve el porcentaje con dos decimales y la flechita") {
            Moneda(variacionEn24hs = -1.56).variacionFormarteada() shouldBe "(-1.56% ▼)"
        }

        // TODO: Agregar tests acá
    }
})