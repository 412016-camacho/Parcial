using CriptoBack.Data.Entities;
using CriptoBack.Data.Entities.Repositories;
using CriptoBack.Services;
using Microsoft.AspNetCore.Mvc;
using System.Drawing;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CriptomonedasParcial.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CriptomonedaController : ControllerBase
    {
        private readonly ICriptoService _service;
        public CriptomonedaController(ICriptoService service)
        {
            _service = service;
        }
        // GET: api/<CriptomonedaController>
        [HttpGet]
        public async Task<IActionResult> Get([FromQuery] Categoria categoria)       //SOLO CARGAR EL ID!!!!
        {
            try
            {
                return Ok(await _service.TraerCategorias(categoria));
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Ha ocurrido un error interno. Error {ex.Message}");
            }

        }

        // GET api/<CriptomonedaController>/5
        //[HttpGet("{id}")]
        //public string Get(int id)
        //{
        //    return "value";
        //}

        // POST api/<CriptomonedaController>
        //[HttpPost]
        //public void Post([FromBody] string value)
        //{
        //}

        // PUT api/<CriptomonedaController>/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromQuery] double valor, [FromQuery] string simbolo, [FromQuery] DateTime fecha)
        {
            try
            {   if (await _service.ActualizarValores(id, valor, simbolo, fecha))
                    return Ok("Criptomoneda actualizada con éxito!");
                else
                    return NotFound("Criptomoneda no encontrada!");
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Ha ocurrido un error interno. Error {ex.Message}");
            }
        }

        // DELETE api/<CriptomonedaController>/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            try
            {
                await _service.CancelarCripto(id);
                return Ok("Criptomoneda eliminada con éxito!");
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Ha ocurrido un error interno. Error {ex.Message}");
            }

        }
    }
}
