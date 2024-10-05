using CriptoBack.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CriptoBack.Services
{
    public interface ICriptoService
    {
        Task<List<Criptomoneda>> TraerCategorias(Categoria categoria);
        Task<bool> ActualizarValores(int id, double valor, string simbolo, DateTime fecha);
        Task<bool> CancelarCripto(int id);
        bool ValidarFecha(DateTime fecha);
    }
}
