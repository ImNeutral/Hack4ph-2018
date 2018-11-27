<?php

namespace App\Http\Middleware;

use Closure;

class Authenticated
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        if ( !session()->has('email') ) {
            echo "sshas email";
            echo session('email');
            return response()->json(['error' => 'Access Denied'], 401);
        }
        return $next($request);
    }
}
